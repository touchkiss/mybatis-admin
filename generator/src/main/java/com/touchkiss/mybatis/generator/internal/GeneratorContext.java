package com.touchkiss.mybatis.generator.internal;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import com.google.common.collect.Maps;
import com.touchkiss.mybatis.generator.internal.db.DatabaseDialects;

import java.util.Map;

public class GeneratorContext {
    public static final String[] DEFAULT_GENERATORS = new String[]{"com.touchkiss.mybatis.generator.template.impl.DaoGenerator", "com.touchkiss.mybatis.generator.template.impl.DomainGenerator", "com.touchkiss.mybatis.generator.template.impl.MappingGenerator", "com.touchkiss.mybatis.generator.template.impl.XmlGenerator", "com.touchkiss.mybatis.generator.template.impl.TablesGenerator", "com.touchkiss.mybatis.generator.template.impl.ServiceGenerator"};
    private String[] generatorClassNames;
    private String generatorRootPath;
    private DatabaseDialects databaseDialect;
    private Map<String, String> packages;
    private Map<String, String[]> generatorConfig;
    private String defaultEntityPackage;
    private boolean forceBigDecimals;
    private Map<String, String> javaTypeChange;
    private String beanNameSuffix;
    private boolean useMark;
    private boolean useLombok;

    private void initDefaultParams() {
        this.generatorConfig.put("XMLGENERATOR_ANNOTATIONS", new String[]{"org.apache.ibatis.annotations.Mapper"});
        this.setBeanNameSuffix("Auto");
    }

    public void setUseMark(boolean useMark) {
        this.useMark = useMark;
    }

    public boolean getUseMark() {
        return this.useMark;
    }

    public boolean isUseLombok() {
        return useLombok;
    }

    public void setUseLombok(boolean useLombok) {
        this.useLombok = useLombok;
    }

    public void setBeanNameSuffix(String beanNameSuffix) {
        this.beanNameSuffix = beanNameSuffix;
    }

    public String getBeanNameSuffix() {
        return this.beanNameSuffix;
    }

    public void addJavaTypeChange(String sourceType, String changeType) {
        this.javaTypeChange.put(sourceType, changeType);
    }

    public Map<String, String> getJavaTypeChange() {
        return this.javaTypeChange;
    }

    public void addGeneratorConfig(String key, String[] value) {
        this.generatorConfig.put(key, value);
    }

    public Map<String, String[]> getGeneratorConfig() {
        return this.generatorConfig;
    }

    public boolean getForceBigDecimals() {
        return this.forceBigDecimals;
    }

    public void setForceBigDecimals(boolean forceBigDecimals) {
        this.forceBigDecimals = forceBigDecimals;
    }

    public String getDefaultEntityPackage() {
        return this.defaultEntityPackage;
    }

    public void setDefaultEntityPackage(String defaultEntityPackage) {
        this.defaultEntityPackage = defaultEntityPackage;
    }

    public String[] getGeneratorClassNames() {
        return this.generatorClassNames;
    }

    public void setGeneratorClassNames(String[] generatorClassNames) {
        this.generatorClassNames = generatorClassNames;
    }

    public Map<String, String> getPackages() {
        return this.packages;
    }

    public void setPackages(Map<String, String> packages) {
        this.packages = packages;
    }

    public String getGeneratorRootPath() {
        return this.generatorRootPath;
    }

    public void setGeneratorRootPath(String generatorRootPath) {
        this.generatorRootPath = generatorRootPath;
    }

    public DatabaseDialects getDatabaseDialect() {
        return this.databaseDialect;
    }

    public String getDatabaseDialectName() {
        return this.databaseDialect.name();
    }

    public void setDatabaseDialect(DatabaseDialects databaseDialects) {
        this.databaseDialect = databaseDialects;
    }

    private GeneratorContext() {
        this.generatorClassNames = DEFAULT_GENERATORS;
        this.generatorConfig = Maps.newHashMap();
        this.forceBigDecimals = true;
        this.javaTypeChange = Maps.newHashMap();
        this.beanNameSuffix = "";
        this.useMark = false;
        this.initDefaultParams();
    }

    public static GeneratorContext createContext(String generatorRootPath, String defaultEntityPackage, Map<String, String> packages) {
        GeneratorContext context = new GeneratorContext();
        context.setGeneratorRootPath(generatorRootPath);
        context.setPackages(packages);
        context.setDefaultEntityPackage(defaultEntityPackage);
        return context;
    }
}
