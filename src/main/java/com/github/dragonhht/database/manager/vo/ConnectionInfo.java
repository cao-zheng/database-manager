package com.github.dragonhht.database.manager.vo;

import com.github.dragonhht.database.manager.common.RelationalPlatform;
import lombok.*;

import java.io.Serializable;

/**
 * 数据库连接信息.
 *
 * @author: huang
 * @Date: 2019-7-9
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionInfo implements Serializable {
    private static final long serialVersionUID = -8327562911854887039L;

    /** 数据库类型. */
    private RelationalPlatform platform;
    /** 连接名. */
    private String name;
    /** 原连接名. */
    private String oldName;
    /** 连接到的主机. */
    private String host;
    /** 端口. */
    private int port;
    /** 用户名. */
    private String userName;
    /** 密码. */
    private String password;
    /** 是否保存密码. */
    private boolean isSavePwd;
    /** 初始数据库. */
    private String db;
    /** 服务名. */
    private String serviceName;
}
