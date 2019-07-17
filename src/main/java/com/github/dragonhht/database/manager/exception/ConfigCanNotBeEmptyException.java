package com.github.dragonhht.database.manager.exception;

/**
 * 配置文件中值为空的异常.
 *
 * @author: huang
 * @Date: 2019-7-10
 */
public class ConfigCanNotBeEmptyException extends RuntimeException {

    public ConfigCanNotBeEmptyException() {
        super("找不到指定的配置信息");
    }

    public ConfigCanNotBeEmptyException(String message) {
        super(message);
    }

    public ConfigCanNotBeEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigCanNotBeEmptyException(Throwable cause) {
        super(cause);
    }

    protected ConfigCanNotBeEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
