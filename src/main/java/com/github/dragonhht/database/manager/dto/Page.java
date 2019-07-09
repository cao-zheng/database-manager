package com.github.dragonhht.database.manager.dto;

import lombok.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 封装分页数据的实体.
 *
 * @author: huang
 * @Date: 2019-7-9
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Page<T> implements Serializable {

    /** 页码. */
    private int pageNo = 1;
    /** 每页最大记录数. */
    private int pageSize = 100;
    /** 总数. */
    private int totalCount;
    /** 总页数. */
    private int totalPage;
    /** 当前页记录. */
    private List<T> list;
    /** sql语句. */
    private SqlStatement sqlStatement;

    /** 其他参数. */
    private Map<String, Object> params = new HashMap<>();

    public void addParam(String key, Object param) {
        this.params.put(key, param);
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        // 计算页数
        int size = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        this.setTotalPage(size);
    }

}
