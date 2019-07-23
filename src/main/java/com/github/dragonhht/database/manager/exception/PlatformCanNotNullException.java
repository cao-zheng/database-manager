package com.github.dragonhht.database.manager.exception;

/**
 * .
 *
 * @author: huang
 * @Date: 2019-7-17
 */
public class PlatformCanNotNullException extends RuntimeException {

    public PlatformCanNotNullException() {
        super("关系型数据库的类型不能为空");
    }

    public PlatformCanNotNullException(String message) {
        super(message);
    }

    public PlatformCanNotNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlatformCanNotNullException(Throwable cause) {
        super(cause);
    }

    protected PlatformCanNotNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
