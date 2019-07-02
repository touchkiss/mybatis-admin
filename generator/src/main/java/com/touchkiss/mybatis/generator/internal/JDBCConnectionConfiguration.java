package com.touchkiss.mybatis.generator.internal;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import java.util.Properties;

public class JDBCConnectionConfiguration {
    private String driverClass;
    private String url;
    private String username;
    private String password;
    private String catalog;
    private String schema;
    private String[] tableType = new String[]{"TABLE", "SYSTEM TABLE"};
    private Properties properties = new Properties();

    public JDBCConnectionConfiguration() {
    }

    public String[] getTableType() {
        return this.tableType;
    }

    public void setTableType(String[] tableType) {
        this.tableType = tableType;
    }

    public String getDriverClass() {
        return this.driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCatalog() {
        return this.catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getSchema() {
        return this.schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public Properties getProperties() {
        return this.properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
