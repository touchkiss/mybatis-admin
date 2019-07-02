package com.touchkiss.mybatis.generator.internal.type;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import com.touchkiss.mybatis.generator.internal.GeneratorContext;

public abstract class CustomTypeProvide {
    protected GeneratorContext context;

    public CustomTypeProvide(GeneratorContext context) {
        this.context = context;
    }
}
