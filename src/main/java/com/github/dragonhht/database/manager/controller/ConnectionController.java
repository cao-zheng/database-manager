package com.github.dragonhht.database.manager.controller;

import com.github.dragonhht.database.manager.common.RelationalPlatform;
import com.github.dragonhht.database.manager.service.RelationalDBService;
import com.github.dragonhht.database.manager.utils.ConnectionInfoUtil;
import com.github.dragonhht.database.manager.utils.DataSourceUtil;
import com.github.dragonhht.database.manager.vo.ConnectionInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 连接信息接口.
 *
 * @author: huang
 * @Date: 2019-7-14
 */
@Slf4j
@RestController
@RequestMapping("/connection")
public class ConnectionController {

    @Autowired
    private RelationalDBService relationalDBService;

    /**
     * 获取所有连接信息.
     * @return
     */
    @GetMapping("/connections")
    public List<ConnectionInfo> getAllConnectionList() throws Exception {
        List<ConnectionInfo> infos = ConnectionInfoUtil.getAllConnection();
        return infos;
    }

    /**
     * 获取单个连接信息.
     * @param name 连接名
     * @return
     * @throws Exception
     */
    @GetMapping("/connection")
    public ConnectionInfo getConnectionInfo(String name) throws Exception {
        return ConnectionInfoUtil.getConnectionByTag(name);
    }

    /**
     * 保存新连接.
     * @param info
     * @return
     */
    @PostMapping("/connection")
    public boolean newConnectionInfo(@RequestBody ConnectionInfo info) {
        boolean ok = false;
        // 获取当前使用的数据源
        String nowDataSource = DataSourceUtil.getNowDataSource();
        RelationalPlatform nowPlatform = DataSourceUtil.getNowPlatform();
        String name = info.getName();
        String nameTemp = name + "-test";
        // 避免覆盖
        info.setName(nameTemp);
        // 将该链接信息设置为新的数据源
        DataSourceUtil.INSTANCE.setNewDataSource(info, true);
        // 测试数据源的正确性
        try {
            relationalDBService.getAllDB();
            ok = true;
        } catch (Exception e) {
            log.error("数据库连接信息错误", e);
        }
        // 将数据源重新设置回来
        if (!StringUtils.isEmpty(nowDataSource)) {
            DataSourceUtil.setNowDataSource(nowDataSource, nowPlatform);
            // 移除测试用的数据源
            DataSourceUtil.INSTANCE.delDataSource(nameTemp);
        }
        if (ok) {
            // 保存数据库连接信息
            info.setName(name);
            ok =  ConnectionInfoUtil.saveConnectionInfo(info);
        }
        return ok;
    }

    /**
     * 删除保存的连接信息
     * @param name
     * @return
     * @throws UnsupportedEncodingException
     */
    @DeleteMapping("/connection")
    public boolean delConnectionInfo(String name) throws UnsupportedEncodingException {
        return ConnectionInfoUtil.delConnectionInfo(name);
    }

}
