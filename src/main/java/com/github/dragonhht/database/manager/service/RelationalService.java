package com.github.dragonhht.database.manager.service;

import com.github.dragonhht.database.manager.dto.PageInfo;

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
    PageInfo select(String sql);

}
