package com.github.dragonhht.database.manager.controller;

import com.github.dragonhht.database.manager.common.RelationalPlatform;
import com.github.dragonhht.database.manager.dto.ResultData;
import com.github.dragonhht.database.manager.service.RelationalDBService;
import com.github.dragonhht.database.manager.service.RelationalService;
import com.github.dragonhht.database.manager.vo.ConnectionInfo;
import com.github.dragonhht.database.manager.vo.DBInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * 数据库相关.
 *
 * @author: huang
 * @Date: 2019-7-15
 */
@RestController
@RequestMapping("/db")
public class DBController {

    @Autowired
    private RelationalDBService relationalDBService;

    /**
     * 通过连接名获取所有可访问的数据库
     * @return
     */
    @PostMapping("/dbs")
    public List<DBInfo> getAllDB(@RequestBody ConnectionInfo info) throws Exception {
        System.out.println(info);
        return relationalDBService.getAllDB(info);
    }

}
