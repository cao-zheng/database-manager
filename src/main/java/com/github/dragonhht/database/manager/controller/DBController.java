package com.github.dragonhht.database.manager.controller;

import com.github.dragonhht.database.manager.service.RelationalDBService;
import com.github.dragonhht.database.manager.vo.ConnectionInfo;
import com.github.dragonhht.database.manager.vo.DBInfo;
import com.github.dragonhht.database.manager.vo.TableInfo;
import com.github.dragonhht.database.manager.vo.ViewInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 通过连接信息获取所有可访问的数据库
     * @return
     */
    @PostMapping("/dbs")
    public List<DBInfo> getAllDB(@RequestBody ConnectionInfo info) throws Exception {
        return relationalDBService.getAllDB(info);
    }

    /**
     * 通过连接信息获取指定数据库下的所有表
     * @param info
     * @return
     */
    @PostMapping("/tables")
    public List<TableInfo> getAllTables(@RequestBody ConnectionInfo info) throws Exception {
        return relationalDBService.getAllTables(info);
    }

    /**
     * 通过连接信息获取指定数据库下的所有视图
     * @param info
     * @return
     * @throws Exception
     */
    @PostMapping("/views")
    public List<ViewInfo> getAllViews(@RequestBody ConnectionInfo info) throws Exception {
        return relationalDBService.getAllViews(info);
    }

    /**
     * 获取当前数据库中的所有表
     * @return
     * @throws Exception
     */
    @GetMapping("/tables")
    public List<TableInfo> getAllNowTables() throws Exception {
        return relationalDBService.getAllNowTables();
    }

    /**
     * 获取当前数据库中的所有视图
     * @return
     * @throws Exception
     */
    @GetMapping("/views")
    public List<ViewInfo> getAllNowViews() throws Exception {
        return relationalDBService.getAllNowViews();
    }

}
