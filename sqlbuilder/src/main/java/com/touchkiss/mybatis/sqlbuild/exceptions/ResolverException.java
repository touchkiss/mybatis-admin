package com.touchkiss.mybatis.sqlbuild.exceptions;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

public class ResolverException extends RuntimeException {
    public ResolverException(String message) {
        super(message);
    }

    public ResolverException(String message, Throwable cause) {
        super(message, cause);
    }
}
