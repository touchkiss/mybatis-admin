package com.touchkiss.mybatis.admin.exception;

/**
 * @Author Touchkiss
 * @create: 2019-06-26 17:37
 */
public class NoSuchTableConfigException extends Exception{
    public NoSuchTableConfigException(String message) {
        super(message);
    }

    public NoSuchTableConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
