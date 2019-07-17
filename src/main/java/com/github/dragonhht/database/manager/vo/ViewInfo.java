package com.github.dragonhht.database.manager.vo;

import lombok.*;

import java.io.Serializable;

/**
 * 视图信息.
 *
 * @author: huang
 * @Date: 2019-7-17
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ViewInfo implements Serializable {
    private static final long serialVersionUID = -5103643348561844833L;

    private String name;
}
