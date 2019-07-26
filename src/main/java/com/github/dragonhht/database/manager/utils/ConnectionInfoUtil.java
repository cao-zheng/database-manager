package com.github.dragonhht.database.manager.utils;

import com.github.dragonhht.database.manager.common.RelationalPlatform;
import com.github.dragonhht.database.manager.model.JdbcConnectionData;
import com.github.dragonhht.database.manager.utils.helper.ConfigHelper;
import com.github.dragonhht.database.manager.vo.ConnectionInfo;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;

/**
 * 处理连接信息的工具类.
 *
 * @author: huang
 * @Date: 2019-7-9
 */
@Slf4j
public final class ConnectionInfoUtil {
    /** 保存连接信息的path. */
    private static final String CONNECTION_INFO_PATH;

    static {
        try {
            CONNECTION_INFO_PATH = ConfigHelper.getConnectionInfoDir();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 保存连接信息到本地.
     * @param info
     * @return
     */
    public static boolean saveConnectionInfo(ConnectionInfo info) {
        try {
            ContentInfo savedInfo = getInfo(info.getOldName());
            StringBuffer sb = new StringBuffer("\n[")
                    .append(info.getName())
                    .append("]\n");
            String result = JsonUtils.INSTANCE.writeValueAsString(info);
            result = DESUtil.getInstance().encode(result.getBytes());
            sb.append(result);
            if (savedInfo.isSaved()) {
                byte[] contentBytes = FileUtil.INSTANCE.readFile(CONNECTION_INFO_PATH);
                String content = new String(contentBytes, "UTF-8");
                String beforeStr = content.substring(0, savedInfo.getStart());
                String afterStr = content.substring(savedInfo.getEnd());
                String msg = beforeStr + sb.toString() + afterStr;
                FileUtil.INSTANCE.writeFile(CONNECTION_INFO_PATH, msg.getBytes(), StandardOpenOption.WRITE);
            } else {
                FileUtil.INSTANCE.writeFile(CONNECTION_INFO_PATH, sb.toString().getBytes(), StandardOpenOption.APPEND);
            }
            return true;
        } catch (Exception e) {
            log.error("保存连接信息失败", e);
        }
        return false;
    }

    /**
     * 删除连接信息.
     * @return
     */
    public static boolean delConnectionInfo(String name) {
        try {
            ContentInfo savedInfo = getInfo(name);
            if (savedInfo.isSaved()) {
                byte[] contentBytes = FileUtil.INSTANCE.readFile(CONNECTION_INFO_PATH);
                String content = new String(contentBytes, "UTF-8");
                String beforeStr = content.substring(0, savedInfo.getStart());
                String afterStr = content.substring(savedInfo.getEnd());
                String msg = beforeStr + afterStr;
                FileUtil.INSTANCE.writeFile(CONNECTION_INFO_PATH, msg.getBytes(), StandardOpenOption.WRITE);
            }
            return true;
        } catch (Exception e) {
            log.error("保存连接信息失败", e);
        }
        return false;
    }

    /**
     * 通过连接名获取连接信息.
     * @param tag
     * @return
     */
    public static ConnectionInfo getConnectionByTag(String tag) throws Exception {
        ContentInfo info = getInfo(tag);
        if (info.isSaved()) {
            byte[] msg = DESUtil.getInstance().decode(info.getMsg().getBytes());
            String json = new String(msg, "UTF-8");
            return JsonUtils.INSTANCE.readValue(json, ConnectionInfo.class);
        }
        return null;
    }

    /**
     * 获取所有已保存的连接信息.
     * @return
     */
    public static List<ConnectionInfo> getAllConnection() throws Exception {
        List<ConnectionInfo> result = new LinkedList<>();
        List<String> msgs = getAllConnectionFromFile(CONNECTION_INFO_PATH);
        if (msgs != null) {
            for (String msg : msgs) {
                String json = new String(DESUtil.getInstance().decode(msg.getBytes()));
                ConnectionInfo info = JsonUtils.INSTANCE.readValue(json, ConnectionInfo.class);
                result.add(info);
            }
        }
        return result;
    }

    private static List<String> getAllConnectionFromFile(String filePath) throws Exception {
        String content = new String(FileUtil.INSTANCE.readFile(filePath));
        List<String> list = new LinkedList<>();
        int index = 0, end = 0;
        while (true) {
            index = content.indexOf("]\n");
            if (index > -1) {
                index += "]\n".length();
                content = content.substring(index);
                end = content.indexOf("\n[");
                if (end > -1) {
                    list.add(content.substring(0, end));
                    content = content.substring(end);
                } else {
                    list.add(content);
                }
            } else {
                break;
            }
        }
        return list;
    }

    /**
     * 通过连接名获取连接信息.
     * @param connectionName
     * @return
     */
    private static ContentInfo getInfo(String connectionName) throws IOException {
        String tag = "\n[" + connectionName + "]\n";
        ContentInfo info = new ContentInfo();
        try {
            String txt = new String(FileUtil.INSTANCE.readFile(CONNECTION_INFO_PATH));
            int index =  txt.indexOf(tag);
            if (index > -1) {
                info.setSaved(true);
                String msg = getConnectionInfo(txt, tag, index, info);
                info.setMsg(msg);
            } else {
                info.setSaved(false);
            }
        } catch (NoSuchFileException e) {
            info.setSaved(false);
            log.error("找不到保存连接信息的文件");
        }
        return info;
    }

    /**
     * 获取以保存的连接信息.
     * @param content
     * @param tag
     * @param index
     * @param info
     * @return
     */
    private static String getConnectionInfo(String content, String tag, int index, ContentInfo info) {
        String contentTemp = content.substring(index + tag.length());
        int end = contentTemp.indexOf("\n[");
        info.setStart(index);
        if (end > -1) {
            info.setEnd(end + tag.length());
            return contentTemp.substring(0, end);
        } else {
            info.setEnd(content.length());
            return contentTemp;
        }
    }

    /**
     * 将连接信息转成jdbc连接信息
     * @param info
     * @return
     */
    public static JdbcConnectionData converInfoToData(ConnectionInfo info) {
        JdbcConnectionData data = new JdbcConnectionData();
        data.setDataBaseName(info.getDb());
        data.setHost(info.getHost());
        data.setPlatform(info.getPlatform());
        data.setPort(info.getPort());
        data.setUserName(info.getUserName());
        data.setPassword(info.getPassword());
        // TODO 暂时只有MySQL
        switch (info.getPlatform()) {
            case MYSQL:
                data.setDriverClassName("com.mysql.cj.jdbc.Driver");
                break;
            default:
                break;
        }
        return data;
    }

    /**
     * 传递连接信息.
     */
    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    static class ContentInfo {
        /** 是否含有信息 */
        private boolean isSaved;
        /** 具体信息. */
        private String msg;
        /** 开始. */
        private int start;
        /** 结束。 */
        private int end;
    }

    public static void main(String[] args) throws Exception {
        /*ConnectionInfo info = new ConnectionInfo();
        info.setUserName("root");
        info.setDb("*");
        info.setHost("my.dragon.com");
        info.setPlatform(RelationalPlatform.MYSQL);
        info.setPort(3307);
        info.setUserName("root");
        info.setPassword("123");
        info.setDbName("link-1");
        saveConnectionInfo(info);
        List<ConnectionInfo> infos = getAllConnection();
        infos.forEach(System.out::println);*/
        delConnectionInfo("ces");
    }

}
