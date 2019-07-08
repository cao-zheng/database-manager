package com.github.dragonhht.database.manager.model;

import lombok.*;

import java.util.List;

/**
 * 查询语句返回结果.
 *
 * @author: huang
 * @Date: 2019-7-8
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResultData {
    /** 字段名. */
    private List<String> columnNames;
    /** 结果. */
    private List<Object> values;

}
