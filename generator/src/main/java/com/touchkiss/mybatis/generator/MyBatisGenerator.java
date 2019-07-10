package com.touchkiss.mybatis.generator;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.touchkiss.mybatis.generator.internal.FullyQualifiedTable;
import com.touchkiss.mybatis.generator.internal.GeneratorContext;
import com.touchkiss.mybatis.generator.internal.JDBCConnectionConfiguration;
import com.touchkiss.mybatis.generator.internal.db.DatabaseDialects;
import com.touchkiss.mybatis.generator.internal.db.DatabaseIntrospector;
import com.touchkiss.mybatis.generator.internal.db.JdbcConnectionFactory;
import com.touchkiss.mybatis.generator.internal.type.FullyQualifiedJavaType;
import com.touchkiss.mybatis.generator.internal.type.JavaTypeResolver;
import com.touchkiss.mybatis.generator.model.*;
import com.touchkiss.mybatis.generator.template.Generator;
import com.touchkiss.mybatis.generator.template.GeneratorResult;
import com.touchkiss.mybatis.generator.template.SingleGenerator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MyBatisGenerator {
    private static final Logger log = LoggerFactory.getLogger(MyBatisGenerator.class);
    private TableConfig[] configs;
    private Class<Generator>[] generators;
    private Class<SingleGenerator>[] singleGenerators;
    private GeneratorContext context;
    private FullyQualifiedTable fullyQualifiedTable;
    private JavaTypeResolver javaTypeResolver;
    private JdbcConnectionFactory jdbcConnectionFactory;
    protected static final String CREATED_INFO = "created...";
    protected static final String CHANGED_INFO = "changed...";
    protected static final String DELETED_INFO = "deleted...";
    protected static final String SKIPD_INFO = "skipd...";

    private void initGenerators() {
        try {
            log.info("load generators...");
            List<Class<?>> generatoreCls = Lists.newArrayList();
            List<Class<?>> singleCls = Lists.newArrayList();
            if (ArrayUtils.isNotEmpty(this.context.getGeneratorClassNames())) {
                String[] generatorClassNames = this.context.getGeneratorClassNames();

                for(int i = 0; i < generatorClassNames.length; ++i) {
                    Class<?> c = Class.forName(generatorClassNames[i]);
                    if (SingleGenerator.class.isAssignableFrom(c)) {
                        singleCls.add(c);
                        log.info("single generators:" + c.getName());
                    } else if (Generator.class.isAssignableFrom(c)) {
                        generatoreCls.add(c);
                        log.info("generators:" + c.getName());
                    }
                }
            }

            this.generators = (Class[])generatoreCls.toArray(new Class[generatoreCls.size()]);
            this.singleGenerators = (Class[])singleCls.toArray(new Class[singleCls.size()]);
        } catch (Exception var6) {
            throw new RuntimeException(var6);
        }
    }

    public MyBatisGenerator(JDBCConnectionConfiguration jdbcConfig, TableConfig[] configs, GeneratorContext context) {
        context.setDatabaseDialect(DatabaseDialects.getDatabaseDialect(jdbcConfig.getUrl()));
        this.context = context;
        this.configs = configs;
        this.initGenerators();
        this.fullyQualifiedTable = new FullyQualifiedTable();
        this.javaTypeResolver = new JavaTypeResolver(context);
        this.jdbcConnectionFactory = JdbcConnectionFactory.getInstance(jdbcConfig);
        TableConfig[] var4 = configs;
        int var5 = configs.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            TableConfig config = var4[var6];
            if (StringUtils.isBlank(config.getPackageName())) {
                config.packageName(this.context.getDefaultEntityPackage());
            }

            this.javaTypeResolver.addDefinedType(config);
        }

    }

    public int generator() {
        log.info("connecting database...");
        Connection connection = null;

        int i;
        try {
            connection = this.jdbcConnectionFactory.getConnection();
            JDBCConnectionConfiguration jdbcConnConfig = this.jdbcConnectionFactory.getConfig();
            DatabaseIntrospector databaseIntrospector = new DatabaseIntrospector(connection.getMetaData(), this.context, jdbcConnConfig.getTableType(), jdbcConnConfig.getCatalog(), jdbcConnConfig.getSchema());

            for(i = 0; i < this.configs.length; ++i) {
                if (!databaseIntrospector.calculateTable(this.configs[i], this.fullyQualifiedTable)) {
                    log.warn("分析表：" + this.configs[i].getTableName() + "错误....");
                } else {
                    databaseIntrospector.calculatePrimaryKey(this.configs[i], this.fullyQualifiedTable);
                }
            }

            databaseIntrospector.calculateForeignKey(this.fullyQualifiedTable, this.javaTypeResolver);
        } catch (SQLException var27) {
            log.error("执行 generator 失败", var27);
        } finally {
            this.jdbcConnectionFactory.closeConnection(connection);
        }

        if (connection == null) {
            return -1;
        } else {
            log.info("analyzing database...");
            Table[] tables = this.fullyQualifiedTable.getTables();
            Table[] var30 = tables;
            i = tables.length;

            int var5;
            for(var5 = 0; var5 < i; ++var5) {
                Table table = var30[var5];
                Column[] columns = this.fullyQualifiedTable.getColumns(table.getTableName());
                TableConfig tableConfig = this.fullyQualifiedTable.getTableConfig(table.getTableName());
                Set<String> imports = Sets.newTreeSet();
                Set<String> javaForeignImports = Sets.newTreeSet();
                Set<String> foreignImportsAll = Sets.newHashSet();
                Column[] var12 = columns;
                int var13 = columns.length;

                for(int var14 = 0; var14 < var13; ++var14) {
                    Column column = var12[var14];
                    CustomColumnType customColumnType = tableConfig.getCustomColumnType(column.getColumnName());
                    FullyQualifiedJavaType javaType;
                    if (customColumnType != null) {
                        javaType = customColumnType.getJavaType();
                        if (customColumnType.getDataType() != null) {
                            column.setDataType(customColumnType.getDataType());
                        }
                    } else {
                        javaType = this.javaTypeResolver.calculateJavaType(column);
                    }

                    column.setJavaType(javaType.getTypeName());
                    column.setFullJavaType(javaType.getFullTypeName());
                    if (StringUtils.isNoneBlank(new CharSequence[]{javaType.getImport()})) {
                        imports.add(javaType.getImport());
                    }

                    if (column.getIsForeignKey()) {
                        javaType = this.javaTypeResolver.calculateJavaType(column, this.fullyQualifiedTable);
                        column.getForeignKey().setForeignType(javaType.getTypeName());
                        column.getForeignKey().setForeignTypeImport(javaType.getImport());
                        if (StringUtils.isNoneBlank(new CharSequence[]{javaType.getImport()})) {
                            if (!javaType.equalsPackage(this.fullyQualifiedTable.getTableConfig(table.getTableName()).getPackageName())) {
                                javaForeignImports.add(javaType.getImport());
                            }

                            foreignImportsAll.add(javaType.getImport());
                        }
                    }

                    List<SelectKey> selectKeys = this.fullyQualifiedTable.getTableConfig(table.getTableName()).getSelectKeys();
                    Iterator var19 = selectKeys.iterator();

                    while(var19.hasNext()) {
                        SelectKey selectKey = (SelectKey)var19.next();
                        if (selectKey.getColumnName().equalsIgnoreCase(column.getColumnName())) {
                            selectKey.setColumn(column);
                        }
                    }
                }

                if (CollectionUtils.isNotEmpty(tableConfig.getCustomProperties())) {
                    Iterator var40 = tableConfig.getCustomProperties().iterator();

                    while(var40.hasNext()) {
                        CustomProperty customProperty = (CustomProperty)var40.next();
                        if (StringUtils.isNotBlank(customProperty.getJavaType().getImport())) {
                            imports.add(customProperty.getJavaType().getImport());
                        }
                    }
                }

                table.setImports((String[])imports.toArray(new String[0]));
                table.setJavaForeignTypeImports((String[])javaForeignImports.toArray(new String[0]));
                table.setForeignTypeImportsAll((String[])foreignImportsAll.toArray(new String[0]));
            }

            log.info("generator...");
            TableConfig[] configs = this.fullyQualifiedTable.getTableConfigs();
            TableConfig[] var32 = configs;
            var5 = configs.length;

            for(int var34 = 0; var34 < var5; ++var34) {
                TableConfig config = var32[var34];
                if (config.getGenerator()) {
                    try {
                        for(int j = 0; j < this.generators.length; ++j) {
                            Generator generator = (Generator)this.generators[j].newInstance();
                            generator.setTableConfig(config);
                            generator.setContext(this.context);
                            generator.setFullyQualifiedTable(this.fullyQualifiedTable);
                            List<GeneratorResult> results = generator.generator();
                            this.generatorFile(results, config.getOverwrite());
                        }
                    } catch (Exception var26) {
                        log.error("执行 generator 失败", var26);
                    }
                }
            }

            try {
                for(i = 0; i < this.singleGenerators.length; ++i) {
                    SingleGenerator generator = (SingleGenerator)this.singleGenerators[i].newInstance();
                    generator.setTableConfig((TableConfig)null);
                    generator.setContext(this.context);
                    generator.setFullyQualifiedTable(this.fullyQualifiedTable);
                    List<GeneratorResult> results = generator.generator();
                    this.generatorFile(results, true);
                }
            } catch (Exception var25) {
                log.error("执行 singleGenerator 失败", var25);
            }

            return 0;
        }
    }

    private void generatorFile(List<GeneratorResult> results, boolean overwrite) throws IOException {
        if (CollectionUtils.isNotEmpty(results)) {
            Iterator var3 = results.iterator();

            while(var3.hasNext()) {
                GeneratorResult result = (GeneratorResult)var3.next();
                if (StringUtils.isNotBlank(result.getText())) {
                    File file = result.getFile();
                    if (!file.exists()) {
                        log.info("created..." + result.getPackageClassName());
                        FileUtils.writeStringToFile(file, result.getText());
                    } else {
                        String preContext = FileUtils.readFileToString(file);
                        if (overwrite) {
                            if (result.getText().equals(preContext)) {
                                log.info("skipd..." + result.getPackageClassName() + "（无需覆盖）");
                            } else {
                                log.info("changed..." + result.getPackageClassName());
                                file.delete();
                                FileUtils.writeStringToFile(file, result.getText());
                            }
                        } else {
                            log.info("skipd..." + result.getPackageClassName() + (result.getText().equals(preContext) ? "" : "（已改变）"));
                        }
                    }
                }
            }
        }

    }
}
