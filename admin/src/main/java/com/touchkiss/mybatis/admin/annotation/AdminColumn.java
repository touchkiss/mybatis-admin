package com.touchkiss.mybatis.admin.annotation;

import java.lang.annotation.*;

/**
 * Created on 2019/7/25 10:03
 *
 * @author Touchkiss
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AdminColumn {
    String comment();

    int length();

    String jdbctype();

    boolean nullable();

    boolean autoIncrement();
}
