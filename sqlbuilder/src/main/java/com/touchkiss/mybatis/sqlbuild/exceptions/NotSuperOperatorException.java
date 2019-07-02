package com.touchkiss.mybatis.sqlbuild.exceptions;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

public class NotSuperOperatorException extends RuntimeException {
    public NotSuperOperatorException(String message) {
        super(message);
    }

    public NotSuperOperatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
