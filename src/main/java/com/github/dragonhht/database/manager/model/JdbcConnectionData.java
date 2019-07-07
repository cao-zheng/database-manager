package com.github.dragonhht.database.manager.model;

import lombok.*;
import org.springframework.util.StringUtils;

/**
 * 连接模型.
 *
 * @author: huang
 * @Date: 2019-7-7
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JdbcConnectionData {

    private String host;
    private int port;
    private String dataBaseName;
    private String userName;
    private String password;
    private String url;
    private String driverClassName;
    private String platform;

    public String getUrl() {
        if (StringUtils.isEmpty(url)) {
            if ("mysql".equalsIgnoreCase(platform)) {
                return "jdbc:mysql://" + host + ":" + port + "/" + dataBaseName + "?useUnicode=true&characterEncoding=utf-8&useSSL=false";
            }
        }
        return this.url;
    }

}
