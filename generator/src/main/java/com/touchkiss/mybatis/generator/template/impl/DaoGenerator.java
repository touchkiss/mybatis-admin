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

public class DaoGenerator extends GeneratorBase {
    private static final Logger log = LoggerFactory.getLogger(DaoGenerator.class);
    public static final String PACKAGE = "daoPackage";
    public static final String PACKAGEIMPL = "daoPackageImpl";
    public static final String ROOTPATH = "daoRootPath";

    public DaoGenerator() {
    }

    private File getFile(String packageName, String suffix) {
        String fileName = this.tableConfig.getEntityName() + suffix + ".java";
        return this.getFilePath((String)this.context.getPackages().get("daoRootPath"), packageName, fileName);
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
        String daoPackage = (String)this.context.getPackages().get("daoPackage");
        String daoPackageImpl = (String)this.context.getPackages().get("daoPackageImpl");
        String mapperPackage = (String)this.context.getPackages().get("mapperPackage");
        params.put("mapperPackage", mapperPackage);
        String beanName;
        if (StringUtils.isNotBlank(daoPackage)) {
            params.put("packageName", daoPackage);
            beanName = FreeMarkerTemplate.format("dao.ftl", params);
            generatorResults.add(new GeneratorResult(this.getFile(daoPackage, this.context.getBeanNameSuffix() + "Dao"), beanName, daoPackage + "." + this.tableConfig.getEntityName() + this.context.getBeanNameSuffix() + "Dao"));
        } else {
            log.error("package not exist daoPackage");
        }

        if (StringUtils.isNotBlank(daoPackageImpl)) {
            params.put("implPackageName", daoPackageImpl);
            beanName = Introspector.decapitalize(this.tableConfig.getEntityName());
            params.put("mapperBeanName", beanName + (this.context.getBeanNameSuffix() != null ? this.context.getBeanNameSuffix() : "") + "Mapper");
            String text = FreeMarkerTemplate.format("daoimpl.ftl", params);
            generatorResults.add(new GeneratorResult(this.getFile(daoPackageImpl, this.context.getBeanNameSuffix() + "DaoImpl"), text, daoPackageImpl + "." + this.tableConfig.getEntityName() + this.context.getBeanNameSuffix() + "DaoImpl"));
        } else {
            log.error("package not exist daoPackageImpl");
        }

        return generatorResults;
    }
}




