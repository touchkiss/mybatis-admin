package com.touchkiss.mybatis.generator.internal.db;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

public enum DatabaseDialects {
    ORACLE,
    MYSQL,
    OTHER;

    private DatabaseDialects() {
    }

    public static DatabaseDialects getDatabaseDialect(String database) {
        DatabaseDialects returnValue;
        if (database.toUpperCase().contains("MySQL".toUpperCase())) {
            returnValue = MYSQL;
        } else if (database.toUpperCase().contains("Oracle".toUpperCase())) {
            returnValue = ORACLE;
        } else {
            returnValue = OTHER;
        }

        return returnValue;
    }
}
