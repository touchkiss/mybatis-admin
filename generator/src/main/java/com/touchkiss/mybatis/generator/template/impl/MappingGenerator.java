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
import com.touchkiss.mybatis.generator.utils.JavaUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.Map;

public class MappingGenerator extends GeneratorBase {
    private static final Logger log = LoggerFactory.getLogger(MappingGenerator.class);
    public static final String PACKAGE = "mapperPackage";
    public static final String ROOTPATH = "mapperRootPath";
    public static final String CONF_ANNOTATIONS = "XMLGENERATOR_ANNOTATIONS";

    public MappingGenerator() {
    }

    private File getFile(String packageName, String suffix) {
        String fileName = this.tableConfig.getEntityName() + suffix + ".java";
        return this.getFilePath((String)this.context.getPackages().get("mapperRootPath"), packageName, fileName);
    }

    @Override
    public List<GeneratorResult> generator() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("table", this.fullyQualifiedTable.getTable(this.tableConfig.getTableName()));
        params.put("tableConfig", this.tableConfig);
        params.put("context", this.context);
        params.put("columns", this.getFilterColumn());
        params.put("entityPackage", this.tableConfig.getPackageName() + "." + this.tableConfig.getEntityName());
        String[] annotations = (String[])this.context.getGeneratorConfig().get("XMLGENERATOR_ANNOTATIONS");
        if (ArrayUtils.isNotEmpty(annotations)) {
            String[] a = new String[annotations.length];

            for(int i = 0; i < a.length; ++i) {
                a[i] = JavaUtils.getClassName(annotations[i]);
            }

            params.put("annotations", a);
            params.put("annotationPackages", annotations);
        }

        List<GeneratorResult> generatorResults = Lists.newArrayList();
        String mapperPackage = (String)this.context.getPackages().get("mapperPackage");
        if (StringUtils.isNotBlank(mapperPackage)) {
            params.put("packageName", mapperPackage);
            String text = FreeMarkerTemplate.format("mapperinterface.ftl", params);
            generatorResults.add(new GeneratorResult(this.getFile(mapperPackage, this.context.getBeanNameSuffix() + "Mapper"), text, mapperPackage + "." + this.tableConfig.getEntityName() + this.context.getBeanNameSuffix() + "Mapper"));
        } else {
            log.error("package not exist mapperPackage");
        }

        return generatorResults;
    }
}
