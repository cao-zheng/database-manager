package com.github.dragonhht.database.manager.service;

import com.github.dragonhht.database.manager.dto.ResultData;
import com.github.dragonhht.database.manager.vo.PageInfo;

import java.util.List;

/**
 * 关系型数据库基本操作服务类接口.
 *
 * @author: huang
 * @Date: 2019-7-7
 */
public interface RelationalService {

    /**
     * 分页查询.
     * @param sql
     * @return
     */
    PageInfo select(String sql) throws Exception;

    /**
     * 查询列表
     * @param sql
     * @return
     */
    List<ResultData> selectList(String sql) throws Exception;

    /**
     * 更新操作
     * @param sql
     * @return
     * @throws Exception
     */
    int update(String sql) throws Exception;

    /**
     * 删除操作
     * @param sql
     * @return
     * @throws Exception
     */
    int delete(String sql) throws Exception;

    /**
     * 插入数据.
     * @param sql
     * @return
     * @throws Exception
     */
    int insert(String sql) throws Exception;

    /**
     * DDL操作
     * @param sql
     * @return
     * @throws Exception
     */
    int ddl(String sql) throws Exception;

}
