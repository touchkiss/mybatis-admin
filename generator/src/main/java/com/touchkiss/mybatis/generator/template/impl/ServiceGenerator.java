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
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.Introspector;
import java.io.File;
import java.util.List;
import java.util.Map;

public class ServiceGenerator extends GeneratorBase {
    private static final Logger log = LoggerFactory.getLogger(ServiceGenerator.class);
    public static final String PACKAGE = "servicePackage";
    public static final String PACKAGEIMPL = "servicePackageImpl";
    public static final String ROOTPATH = "serviceRootPath";

    public ServiceGenerator() {
    }

    private File getFile(String packageName, String suffix) {
        String fileName = this.tableConfig.getEntityName() + suffix + ".java";
        return this.getFilePath((String)this.context.getPackages().get("serviceRootPath"), packageName, fileName);
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
        List<Column> foreignColumns = this.getForeignColumns(columns);
        params.put("foreignColumns", foreignColumns);
        List<GeneratorResult> generatorResults = Lists.newArrayList();
        String servicePackage = (String)this.context.getPackages().get("servicePackage");
        String servicePackageImpl = (String)this.context.getPackages().get("servicePackageImpl");
        String mapperPackage = (String)this.context.getPackages().get("mapperPackage");
        params.put("mapperPackage", mapperPackage);
        String beanName;
        if (StringUtils.isNotBlank(servicePackage)) {
            params.put("packageName", servicePackage);
            beanName = FreeMarkerTemplate.format("service.ftl", params);
            generatorResults.add(new GeneratorResult(this.getFile(servicePackage, "Service"), beanName, servicePackage + "." + this.tableConfig.getEntityName() + this.context.getBeanNameSuffix() + "Service"));
        } else {
            log.error("package not exist servicePackage");
        }

        if (StringUtils.isNotBlank(servicePackageImpl)) {
            params.put("daoPackageName", this.context.getPackages().get("daoPackage"));
            params.put("implPackageName", servicePackageImpl);
            beanName = Introspector.decapitalize(this.tableConfig.getEntityName());
            params.put("mapperBeanName", beanName + (this.context.getBeanNameSuffix() != null ? this.context.getBeanNameSuffix() : "") + "Mapper");
            String text = FreeMarkerTemplate.format("serviceimpl.ftl", params);
            generatorResults.add(new GeneratorResult(this.getFile(servicePackageImpl, "ServiceImpl"), text, servicePackageImpl + "." + this.tableConfig.getEntityName() + this.context.getBeanNameSuffix() + "ServiceImpl"));
        } else {
            log.error("package not exist servicePackageImpl");
        }

        return generatorResults;
    }
}
