package com.touchkiss.mybatis.admin.annotation;

import java.lang.annotation.*;

/**
 * Created on 2019/08/02 17:35
 *
 * @author Touchkiss
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AdminBean {
    String value();
}
