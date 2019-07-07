package com.github.dragonhht.database.manager.service;

import java.util.List;
import java.util.Map;

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
    List<List<Object>> select(String sql);

}
