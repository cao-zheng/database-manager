package com.github.dragonhht.database.manager.utils;

import com.github.dragonhht.database.manager.model.JdbcConnectionData;
import com.github.dragonhht.database.manager.vo.ConnectionInfo;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据源工具.
 *
 * @author: huang
 * @Date: 2019-7-7
 */
public enum  DataSourceUtil {

    /** 实例. */
    INSTANCE;

    /** 用于存储数据源. */
    private Map<String, DataSource> dataSourceMap = new ConcurrentHashMap<>();

    /** 当前用户所使用的的数据源. */
    private static String nowDataSource;

    /**
     * 添加数据源.
     * @param key 数据源唯一键
     * @param dataSource 数据源
     */
    public void addDataSource(String key, DataSource dataSource) {
        dataSourceMap.put(key, dataSource);
    }

    /**
     * 添加数据源.
     * @param key 数据源唯一键
     * @param data 数据库连接信息
     */
    public void addDataSource(String key, JdbcConnectionData data) {
        this.addDataSource(key, this.initDataSource(data));
    }

    /**
     * 通过键获取数据源.
     * @param key
     * @return
     */
    public DataSource getDataSource(String key) {
        return dataSourceMap.get(key);
    }

    /**
     * 移除数据源.
     * @param key
     */
    public void delDataSource(String key) {
        dataSourceMap.remove(key);
    }

    /**
     * 初始化数据源.
     * @param data
     * @return
     */
    public DataSource initDataSource(JdbcConnectionData data) {
        HikariDataSource dataSource = DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .driverClassName(data.getDriverClassName())
                .url(data.getUrl())
                .username(data.getUserName())
                .password(data.getPassword())
                .build();
        return dataSource;
    }

    /**
     * 通过连接信息直接设置当前使用的数据源
     * @param info
     */
    public void setNowDataSource(ConnectionInfo info) {
        if (dataSourceMap.get(info.getName()) != null) {
            setNowDataSource(info.getName());
            return;
        }
        JdbcConnectionData data = ConnectionInfoUtil.converInfoToData(info);
        DataSource dataSource = initDataSource(data);
        dataSourceMap.put(info.getName(), dataSource);
        setNowDataSource(info.getName());
    }

    /** 设置当前使用的数据源. */
    public static String getNowDataSource() {
        return nowDataSource;
    }

    /** 获取当前使用的数据源. */
    public static void setNowDataSource(String nowDataSource) {
        DataSourceUtil.nowDataSource = nowDataSource;
    }
}
