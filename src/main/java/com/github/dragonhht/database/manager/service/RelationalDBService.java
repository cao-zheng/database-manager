package com.github.dragonhht.database.manager.service;

import com.github.dragonhht.database.manager.vo.ConnectionInfo;
import com.github.dragonhht.database.manager.vo.DBInfo;
import com.github.dragonhht.database.manager.vo.TableInfo;
import com.github.dragonhht.database.manager.vo.ViewInfo;

import java.util.List;

/**
 * 关系型数据库的数据库相关操作.
 *
 * @author: huang
 * @Date: 2019-7-15
 */
public interface RelationalDBService {

    /**
     * 通过连接信息获取数据库
     * @param connectionInfo
     * @return
     * @throws Exception
     */
    List<DBInfo> getAllDB(ConnectionInfo connectionInfo) throws Exception;

    /**
     * 获取指定数据库下的表
     * @param info
     * @return
     * @throws Exception
     */
    List<TableInfo> getAllTables(ConnectionInfo info) throws Exception;

    /**
     * 获取指定数据库下的视图
     * @param info
     * @return
     * @throws Exception
     */
    List<ViewInfo> getAllViews(ConnectionInfo info) throws Exception;

    /**
     * 获取当前数据库下的所有表
     * @return
     * @throws Exception
     */
    List<TableInfo> getAllNowTables() throws Exception;

    /**
     * 获取当前数据库下的所有视图
     * @return
     * @throws Exception
     */
    List<ViewInfo> getAllNowViews() throws Exception;

}
