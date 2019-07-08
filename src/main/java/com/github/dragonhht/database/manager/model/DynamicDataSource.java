package com.github.dragonhht.database.manager.model;

import com.github.dragonhht.database.manager.utils.DataSourceUtil;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.*;

/**
 * 动态数据库源.
 *
 * @author: huang
 * @Date: 2019-7-7
 */
@Slf4j
public class DynamicDataSource extends HikariDataSource {

    @Override
    public Connection getConnection() throws SQLException {
        // 当前用户所使用的数据源的键
        String nowKey = DataSourceUtil.getNowDataSource();
        DataSource dataSource = null;
        if (nowKey != null) {
            dataSource = DataSourceUtil.INSTANCE.getDataSource(nowKey);
        }
        if (dataSource == null) {
            log.error("数据源未创建");
            return null;
        } else {
            Connection connection = dataSource.getConnection();
            System.out.println(connection.getMetaData());
            return connection;
        }
    }
}
