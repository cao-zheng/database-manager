package com.github.dragonhht.database.manager.exception;

/**
 * 未加载文件异常.
 *
 * @author: huang
 * @Date: 2019-7-9
 */
public class NotLoadFileException extends RuntimeException {

    public NotLoadFileException() {
        super("文件未正常加载");
    }

    public NotLoadFileException(String message) {
        super(message);
    }

    public NotLoadFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotLoadFileException(Throwable cause) {
        super(cause);
    }

    public NotLoadFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}