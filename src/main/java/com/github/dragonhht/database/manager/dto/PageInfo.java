package com.github.dragonhht.database.manager.dto;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装分页查询的实体(用于返回给控制层).
 *
 * @author: huang
 * @Date: 2019-7-9
 */
@Getter
@Setter
@ToString
public class PageInfo implements Serializable {
    private static final long serialVersionUID = -6376874845644129892L;

    /** 页码. */
    private int pageNo = 1;
    /** 每页最大记录数. */
    private int pageSize = 100;
    /** 总数. */
    private int totalCount;
    /** 总页数. */
    private int totalPage;
    /** 当前页记录. */
    private List<ResultData> list;
    /** 查询的字段. */
    private List<String> columns;

    public PageInfo(Page page, List<ResultData> list) {
        this.pageNo = page.getPageNo();
        this.pageSize = page.getPageSize();
        this.totalCount = page.getTotalCount();
        this.totalPage = page.getTotalPage();
        if (list != null) {
            int listSize = list.size();
            if (listSize > 0) {
                ResultData columnData = list.get(0);
                this.columns = columnData.getColumnNames();
            }
            if (listSize > 1) {
                list.remove(0);
                this.list = list;
            }
        }
    }
}
