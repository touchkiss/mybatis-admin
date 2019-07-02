package com.touchkiss.mybatis.admin.exception;

/**
 * @Author Touchkiss
 * @create: 2019-06-26 17:03
 */
public class ErrorParseSelectorException extends Exception {
    public ErrorParseSelectorException(String message) {
        super(message);
    }

    public ErrorParseSelectorException(String message, Throwable cause) {
        super(message, cause);
    }
}
