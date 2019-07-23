package com.github.dragonhht.database.manager.vo;

import lombok.*;

import java.io.Serializable;

/**
 * 关系型数据库表信息.
 *
 * @author: huang
 * @Date: 2019-7-17
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TableInfo implements Serializable {
    private static final long serialVersionUID = 3104301924462458452L;

    /** 表名称. */
    private String name;
}
