package com.github.dragonhht.database.manager.vo;

import lombok.*;

import java.io.Serializable;

/**
 * .
 *
 * @author: huang
 * @Date: 2019-7-29
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReviceInfo<T> implements Serializable {
    private static final long serialVersionUID = -6293813271820529088L;

    private T info;
    private ConnectionInfo connectionInfo;
}
