package com.github.dragonhht.database.manager.common;

/**
 * 关系型数据库类型.
 *
 * @author: huang
 * @Date: 2019-7-9
 */
public enum  RelationalPlatform {

    /** MySQL. */
    MYSQL("MySQL"),
    /** Oracle. */
    ORACLE("Oracle"),
    /** PostgreSQL. */
    POSTGRESQL("PostgreSQL"),
    /** SQL Server. */
    SQLSERVER("SQLServer"),
    /** SQLite. */
    SQLITE("SQLite"),
    /** MariaDB. */
    MARIADB("MariaDB");

    private String value;

    RelationalPlatform(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
