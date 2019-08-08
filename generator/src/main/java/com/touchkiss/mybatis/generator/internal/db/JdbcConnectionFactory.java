package com.touchkiss.mybatis.generator.internal.db;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import com.touchkiss.mybatis.generator.internal.JDBCConnectionConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConnectionFactory {
    private static final Logger log = LoggerFactory.getLogger(JdbcConnectionFactory.class);
    private static JdbcConnectionFactory instance;
    private static final Object LOCK = new Object();
    private JDBCConnectionConfiguration config;

    public static JdbcConnectionFactory getInstance(JDBCConnectionConfiguration config) {
        if (config == null) {
            throw new RuntimeException("数据库配置出错！");
        } else {
            if (instance == null) {
                Object var1 = LOCK;
                synchronized(LOCK) {
                    instance = new JdbcConnectionFactory(config);
                }
            }

            return instance;
        }
    }

    private JdbcConnectionFactory(JDBCConnectionConfiguration config) {
        this.config = config;
    }

    public Connection getConnection() throws SQLException {
        Driver driver = this.getDriver(this.config);
        Properties props = new Properties();
        if (StringUtils.isNotBlank(this.config.getUsername())) {
            props.setProperty("user", this.config.getUsername());
        }

        if (StringUtils.isNotBlank(this.config.getPassword())) {
            props.setProperty("password", this.config.getPassword());
        }

        props.putAll(this.config.getProperties());
        Connection conn = driver.connect(this.config.getUrl(), props);
        if (conn == null) {
            throw new SQLException("连接数据库失败");
        } else {
            return conn;
        }
    }

    public boolean closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                return true;
            }
        } catch (SQLException var3) {
            log.error("关闭数据库连接出错", var3);
        }

        return false;
    }

    public JDBCConnectionConfiguration getConfig() {
        return this.config;
    }

    private Driver getDriver(JDBCConnectionConfiguration connectionInformation) {
        String driverClass = connectionInformation.getDriverClass();

        try {
            Class<?> clazz = Class.forName(driverClass);
            Driver driver = (Driver)clazz.newInstance();
            return driver;
        } catch (Exception var5) {
            throw new RuntimeException("加载数据库驱动失败", var5);
        }
    }
}
