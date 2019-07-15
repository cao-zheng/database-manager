package com.github.dragonhht.database.manager.service;

import com.github.dragonhht.database.manager.common.RelationalPlatform;
import com.github.dragonhht.database.manager.vo.ConnectionInfo;
import com.github.dragonhht.database.manager.vo.DBInfo;

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

}
