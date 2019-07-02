package com.touchkiss.mybatis.generator.template.impl;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.touchkiss.mybatis.generator.freemarker.FreeMarkerTemplate;
import com.touchkiss.mybatis.generator.template.GeneratorBase;
import com.touchkiss.mybatis.generator.template.GeneratorResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.Map;

public class DomainGenerator extends GeneratorBase {
    private static final Logger log = LoggerFactory.getLogger(DomainGenerator.class);

    public DomainGenerator() {
    }

    private File getFile() {
        String packageName = this.tableConfig.getPackageName();
        String fileName = this.tableConfig.getEntityName() + ".java";
        return this.getFilePath(packageName, fileName);
    }

    @Override
    public List<GeneratorResult> generator() {
        try {
            Map<String, Object> params = Maps.newHashMap();
            params.put("table", this.fullyQualifiedTable.getTable(this.tableConfig.getTableName()));
            params.put("tableConfig", this.tableConfig);
            params.put("context", this.context);
            params.put("columns", this.getFilterColumn());
            return Lists.newArrayList(new GeneratorResult[]{new GeneratorResult(this.getFile(), FreeMarkerTemplate.format("entity.ftl", params), this.tableConfig.getPackageName() + "." + this.tableConfig.getEntityName())});
        } catch (Exception var2) {
            log.error("generator", var2);
            return null;
        }
    }
}
