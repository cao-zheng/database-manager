package com.github.dragonhht.database.manager.vo;

import com.github.dragonhht.database.manager.common.RelationalPlatform;
import lombok.*;

import java.io.Serializable;

/**
 * 数据库信息.
 *
 * @author: huang
 * @Date: 2019-7-15
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DBInfo implements Serializable {
    private static final long serialVersionUID = 6401285815205620464L;

    /** 数据库名称. */
    private String dbName;
    /** 数据库类型. */
    private RelationalPlatform platform;
    /** 字符集. */
    private String charset;
}
