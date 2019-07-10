package com.github.dragonhht.database.manager.utils.helper;

import com.github.dragonhht.database.manager.exception.ConfigCanNotBeEmpty;
import com.github.dragonhht.database.manager.exception.NotLoadFileException;
import com.github.dragonhht.database.manager.utils.ConfigProUtil;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * 获取配置信息的助手类.
 *
 * @author: huang
 * @Date: 2019-7-10
 */
public final class ConfigHelper {

    private static final ConfigProUtil CONFIG_UTIL;

    static {
        String path = System.getProperty("user.dir") + File.separator + "conf" + File.separator
                + "conf.properties";
        try {
            CONFIG_UTIL = new ConfigProUtil(path);
        } catch (IOException e) {
            throw new NotLoadFileException("配置文件未找到", e);
        }
    }

    /**
     * 获取保存连接信息的目录.
     * @return
     */
    public static String getConnectionInfoDir() throws IOException {
        String key = "relational.connection.info.data.dir";
        String value = CONFIG_UTIL.getValue(key);
        if (StringUtils.isEmpty(value)) {
            throw new ConfigCanNotBeEmpty("配置文件中 " + key + " 的值不能为空");
        } else {
            return getFileAbsolutePath(value);
        }
    }

    /**
     * 获取文件的绝对路径.
     * @param path
     * @return
     * @throws IOException
     */
    private static String getFileAbsolutePath(String path) throws IOException {
        return new File(path).getCanonicalPath();
    }

}
