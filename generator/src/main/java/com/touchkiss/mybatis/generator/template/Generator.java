package com.touchkiss.mybatis.generator.template;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import com.touchkiss.mybatis.generator.internal.FullyQualifiedTable;
import com.touchkiss.mybatis.generator.internal.GeneratorContext;
import com.touchkiss.mybatis.generator.model.TableConfig;

import java.util.List;

public interface Generator {
    List<GeneratorResult> generator() throws Exception;

    void setTableConfig(TableConfig var1);

    void setFullyQualifiedTable(FullyQualifiedTable var1);

    void setContext(GeneratorContext var1);
}
