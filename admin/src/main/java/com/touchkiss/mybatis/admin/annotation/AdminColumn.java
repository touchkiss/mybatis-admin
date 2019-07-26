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
    String comment() default "";

    int length() default 0;

    String jdbctype() default "";

    boolean nullable() default true;

    boolean autoIncrement() default false;

    String defaultValue() default "";
}
