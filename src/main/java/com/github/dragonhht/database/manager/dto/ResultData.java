package com.github.dragonhht.database.manager.dto;

import lombok.*;

import java.io.Serializable;
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
public class ResultData implements Serializable {
    private static final long serialVersionUID = -438604185356336868L;

    /** 字段名. */
    private List<String> columnNames;
    /** 结果. */
    private List<Object> values;

}
