package com.github.dragonhht.database.manager.service;

import com.github.dragonhht.database.manager.common.RelationalPlatform;
import com.github.dragonhht.database.manager.vo.PageInfo;

/**
 * 关系型数据库表的增删改查操作接口.
 *
 * @author: huang
 * @Date: 2019-7-18
 */
public interface RelationalTableService {

    /**
     * 获取指定表中的所有数据
     * @param dataSourceName
     * @param tableName
     * @return
     * @throws Exception
     */
    PageInfo getAllData(String dataSourceName, RelationalPlatform platform, String tableName) throws Exception;

}
