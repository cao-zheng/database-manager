package com.github.dragonhht.database.manager.model;

import com.github.dragonhht.database.manager.common.RelationalPlatform;
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
    private RelationalPlatform platform;

    public String getUrl() {
        if (StringUtils.isEmpty(url)) {
            if (platform == RelationalPlatform.MYSQL) {
                if(!StringUtils.isEmpty(dataBaseName) && !"*".equalsIgnoreCase(dataBaseName)) {
                    return "jdbc:mysql://" + host + ":" + port + "/" + dataBaseName + "?useUnicode=true&characterEncoding=utf-8&useSSL=false";
                } else {
                    return "jdbc:mysql://" + host + ":" + port + "?useUnicode=true&characterEncoding=utf-8&useSSL=false";
                }
            }
        }
        return this.url;
    }

}
