package com.github.dragonhht.database.manager.controller;

import com.github.dragonhht.database.manager.utils.ConnectionInfoUtil;
import com.github.dragonhht.database.manager.vo.ConnectionInfo;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
    @DeleteMapping("/connection/{name}")
    public boolean delConnectionInfo(@PathVariable("name") String name) throws UnsupportedEncodingException {
        name = URLDecoder.decode(name, "UTF-8");
        return ConnectionInfoUtil.delConnectionInfo(name);
    }

}
