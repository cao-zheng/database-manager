package com.github.dragonhht.database.manager.service.impl;

import com.github.dragonhht.database.manager.common.RelationalPlatform;
import com.github.dragonhht.database.manager.service.RelationalService;
import com.github.dragonhht.database.manager.service.RelationalTableService;
import com.github.dragonhht.database.manager.utils.DataSourceUtil;
import com.github.dragonhht.database.manager.vo.PageInfo;
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
}
