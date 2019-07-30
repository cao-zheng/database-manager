package com.github.dragonhht.database.manager.service.impl;

import com.github.dragonhht.database.manager.common.RelationalPlatform;
import com.github.dragonhht.database.manager.service.RelationalService;
import com.github.dragonhht.database.manager.service.RelationalTableService;
import com.github.dragonhht.database.manager.utils.DataSourceUtil;
import com.github.dragonhht.database.manager.vo.ConnectionInfo;
import com.github.dragonhht.database.manager.vo.PageInfo;
import com.github.dragonhht.database.manager.vo.TableInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * .
 *
 * @author: huang
 * @Date: 2019-7-18
 */
@Service
public class RelationalTableServiceImpl implements RelationalTableService {

    @Autowired
    private RelationalService relationalService;


    @Override
    public PageInfo getAllData(String dataSourceName, RelationalPlatform platform, String tableName) throws Exception {
        DataSourceUtil.setNowDataSource(dataSourceName, platform);
        return relationalService.select("select * from " + tableName);
    }

    @Override
    public boolean createTable(TableInfo tableInfo, ConnectionInfo connectionInfo) throws Exception {
        boolean ok = false;
        // TODO 新建表逻辑
        return ok;
    }
}
