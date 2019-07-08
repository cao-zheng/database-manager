package com.github.dragonhht.database.manager.service;

import com.github.dragonhht.database.manager.model.ResultData;

import java.util.List;

/**
 * .
 *
 * @author: huang
 * @Date: 2019-7-7
 */
public interface RelationalService {

    /**
     * 查询.
     * @param sql
     * @return
     */
    List<ResultData> select(String sql);

}
