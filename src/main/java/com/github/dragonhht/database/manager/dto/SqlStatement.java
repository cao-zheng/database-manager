package com.github.dragonhht.database.manager.dto;

import lombok.*;

import java.io.Serializable;

/**
 * .
 *
 * @author: huang
 * @Date: 2019-7-7
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SqlStatement implements Serializable {
    private static final long serialVersionUID = 6160229367058830875L;
    /** sql语句. */
    private String sql;
    /** 平台, 如 MySQL、Oracle等. */
    private String platform = "MySQL";

    public SqlStatement(String sql) {
        this.sql = sql;
    }
}
