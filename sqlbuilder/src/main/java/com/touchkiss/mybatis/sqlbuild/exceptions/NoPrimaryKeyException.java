package com.touchkiss.mybatis.sqlbuild.exceptions;

/**
 * Created on 2019/08/21 14:58
 *
 * @author Touchkiss
 */
public class NoPrimaryKeyException extends RuntimeException {
    public NoPrimaryKeyException(String message) {
        super(message);
    }

    public NoPrimaryKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}
