package com.github.dragonhht.database.manager.controller;

import com.github.dragonhht.database.manager.common.RelationalPlatform;
import com.github.dragonhht.database.manager.service.RelationalTableService;
import com.github.dragonhht.database.manager.vo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 表的增删改查操作.
 *
 * @author: huang
 * @Date: 2019-7-18
 */
@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    private RelationalTableService relationalTableService;

    /**
     * 获取指定表中的所有数据
     * @param dataSourceName
     * @param platform
     * @param tableName
     * @return
     */
    @PostMapping("/all/data")
    public PageInfo getAllData(String dataSourceName, RelationalPlatform platform, String tableName)
            throws Exception {
        return relationalTableService.getAllData(dataSourceName, platform, tableName);
    }

}
