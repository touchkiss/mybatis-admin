package com.touchkiss.mybatis.generator.template.impl;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.touchkiss.mybatis.generator.freemarker.FreeMarkerTemplate;
import com.touchkiss.mybatis.generator.model.Column;
import com.touchkiss.mybatis.generator.template.GeneratorBase;
import com.touchkiss.mybatis.generator.template.GeneratorResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.Map;

public class XmlGenerator extends GeneratorBase {
    private static final Logger log = LoggerFactory.getLogger(XmlGenerator.class);
    public static final String PACKAGE = "xmlPackage";
    public static final String ROOTPATH = "xmlRootPath";

    public XmlGenerator() {
    }

    private File getFile(String packageName, String suffix) {
        String fileName = this.tableConfig.getEntityName() + suffix + ".xml";
        return this.getFilePath((String)this.context.getPackages().get("xmlRootPath"), packageName, fileName);
    }

    @Override
    public List<GeneratorResult> generator() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("table", this.fullyQualifiedTable.getTable(this.tableConfig.getTableName()));
        params.put("tableConfig", this.tableConfig);
        params.put("context", this.context);
        List<Column> columns = this.getFilterColumn();
        params.put("columns", columns);
        List<Column> primaryKeyColumns = this.getPrimaryKeyColumn(columns);
        params.put("primaryKeyColumns", primaryKeyColumns);
        params.put("entityPackage", this.tableConfig.getPackageName() + "." + this.tableConfig.getEntityName());
        List<GeneratorResult> generatorResults = Lists.newArrayList();
        String packageName = (String)this.context.getPackages().get("xmlPackage");
        String mapperPackage = (String)this.context.getPackages().get("mapperPackage");
        params.put("mapperPackage", mapperPackage + "." + this.tableConfig.getEntityName() + this.context.getBeanNameSuffix() + "Mapper");
        params.put("packageName", packageName);
        String text = FreeMarkerTemplate.format("xml.ftl", params);
        generatorResults.add(new GeneratorResult(this.getFile(packageName, this.context.getBeanNameSuffix() + "Mapper"), text, packageName + "." + this.tableConfig.getEntityName() + this.context.getBeanNameSuffix() + "Mapper"));
        return generatorResults;
    }
}