package com.github.dragonhht.database.manager.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.dragonhht.database.manager.common.RelationalPlatform;
import com.github.dragonhht.database.manager.vo.ConnectionInfo;

import java.io.IOException;
import java.nio.file.StandardOpenOption;

/**
 * 处理连接信息的工具类.
 *
 * @author: huang
 * @Date: 2019-7-9
 */
public final class ConnectionInfoUtil {

    private static final ConfigProUtil CONFIG_PRO_UTIL;

    static {
        try {
            // TODO 配置文件路径先写死
            CONFIG_PRO_UTIL = new ConfigProUtil("D:\\my_work_spance\\idea_workspance\\database-manager\\src\\main\\resources\\conf\\conf.properties");
        } catch (IOException e) {
            throw new RuntimeException("加载配置文件失败", e);
        }
    }

    /**
     * 保存连接信息到本地.
     * @param info
     * @return
     */
    public static boolean saveConnectionInfo(ConnectionInfo info) {
        try {
            String result = JsonUtils.INSTANCE.writeValueAsString(info);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        /*ConnectionInfo info = new ConnectionInfo();
        info.setHost("my.dragon.com");
        info.setName("我的连接");
        info.setPassword("123");
        info.setPlatform(RelationalPlatform.MYSQL);
        info.setPort(3307);
        info.setSavePwd(false);
        info.setUserName("root");
        saveConnectionInfo(info);*/
        FileUtil.INSTANCE.writeFile("D:\\my_work_spance\\idea_workspance\\database-manager\\src\\main\\resources\\conf\\confs.properties",
                "\nvery good".getBytes(), StandardOpenOption.WRITE);
    }

}
