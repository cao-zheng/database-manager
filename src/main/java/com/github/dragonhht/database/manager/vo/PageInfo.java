package com.github.dragonhht.database.manager.vo;

import com.github.dragonhht.database.manager.dto.Page;
import com.github.dragonhht.database.manager.dto.ResultData;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    private List<Map<String, Object>> list;
    /** 查询的字段. */
    private List<String> columns;

    public PageInfo(Page page, List<ResultData> list) {
        this.pageNo = page.getPageNo();
        this.pageSize = page.getPageSize();
        this.totalCount = page.getTotalCount();
        this.totalPage = page.getTotalPage();
        this.list = covertResultDataToMap(list);
    }

    private List<Map<String, Object>> covertResultDataToMap(List<ResultData> dataList) {
        List<String> columns = new LinkedList<>();
        List<Map<String, Object>> results = new LinkedList<>();
        if (dataList != null) {
            int listSize = dataList.size();
            if (listSize > 0) {
                ResultData columnData = dataList.get(0);
                columns = columnData.getColumnNames();
                this.columns = columns;
            }
            if (listSize > 1) {
                for (int i = 1; i < listSize; i++) {
                    List<Object> datas = dataList.get(i).getValues();
                    if (datas != null) {
                        Map<String, Object> map = new HashMap<>();
                        for (int j = 0; j < columns.size(); j++) {
                            map.put(columns.get(j), datas.get(j));
                        }
                        results.add(map);
                    }
                }
            }
        }
        return results;
    }
}
