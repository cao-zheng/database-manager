package com.github.dragonhht.database.manager.controller;

import com.github.dragonhht.database.manager.utils.ConnectionInfoUtil;
import com.github.dragonhht.database.manager.vo.ConnectionInfo;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 连接信息接口.
 *
 * @author: huang
 * @Date: 2019-7-14
 */
@RestController
@RequestMapping("/connection")
public class ConnectionController {

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
    public boolean newConnectionInfo(ConnectionInfo info) {
        return ConnectionInfoUtil.saveConnectionInfo(info);
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
