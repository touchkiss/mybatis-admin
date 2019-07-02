package com.touchkiss.mybatis.admin.exception;

/**
 * @Author Touchkiss
 * @create: 2019-06-26 10:08
 */
public class ErrorCompareValueException extends Exception{
    public ErrorCompareValueException(String message) {
        super(message);
    }

    public ErrorCompareValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
