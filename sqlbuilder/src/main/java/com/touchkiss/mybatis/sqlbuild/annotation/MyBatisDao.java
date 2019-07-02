package com.touchkiss.mybatis.sqlbuild.annotation;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Component
public @interface MyBatisDao {
    String value() default "";
}
