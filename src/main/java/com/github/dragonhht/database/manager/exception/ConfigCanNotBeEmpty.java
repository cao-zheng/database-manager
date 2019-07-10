package com.github.dragonhht.database.manager.exception;

/**
 * 配置文件中值为空的异常.
 *
 * @author: huang
 * @Date: 2019-7-10
 */
public class ConfigCanNotBeEmpty extends RuntimeException {

    public ConfigCanNotBeEmpty() {
        super();
    }

    public ConfigCanNotBeEmpty(String message) {
        super(message);
    }

    public ConfigCanNotBeEmpty(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigCanNotBeEmpty(Throwable cause) {
        super(cause);
    }

    protected ConfigCanNotBeEmpty(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
