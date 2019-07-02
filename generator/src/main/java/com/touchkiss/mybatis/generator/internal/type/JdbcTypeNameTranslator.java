package com.touchkiss.mybatis.generator.internal.type;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import java.util.HashMap;
import java.util.Map;

public class JdbcTypeNameTranslator {
    private static Map<Integer, String> typeToName = new HashMap();
    private static Map<String, Integer> nameToType;

    private JdbcTypeNameTranslator() {
    }

    public static String getJdbcTypeName(int jdbcType) {
        String answer = (String)typeToName.get(jdbcType);
        if (answer == null) {
            answer = "OTHER";
        }

        return answer;
    }

    public static int getJdbcType(String jdbcTypeName) {
        Integer answer = (Integer)nameToType.get(jdbcTypeName);
        if (answer == null) {
            answer = 1111;
        }

        return answer;
    }

    static {
        typeToName.put(2003, "ARRAY");
        typeToName.put(-5, "BIGINT");
        typeToName.put(-2, "BINARY");
        typeToName.put(-7, "BIT");
        typeToName.put(2004, "BLOB");
        typeToName.put(16, "BOOLEAN");
        typeToName.put(1, "CHAR");
        typeToName.put(2005, "CLOB");
        typeToName.put(70, "DATALINK");
        typeToName.put(91, "DATE");
        typeToName.put(3, "DECIMAL");
        typeToName.put(2001, "DISTINCT");
        typeToName.put(8, "DOUBLE");
        typeToName.put(6, "FLOAT");
        typeToName.put(4, "INTEGER");
        typeToName.put(2000, "JAVA_OBJECT");
        typeToName.put(-4, "LONGVARBINARY");
        typeToName.put(-1, "LONGVARCHAR");
        typeToName.put(-15, "NCHAR");
        typeToName.put(2011, "NCLOB");
        typeToName.put(-9, "NVARCHAR");
        typeToName.put(-16, "LONGNVARCHAR");
        typeToName.put(0, "NULL");
        typeToName.put(2, "NUMERIC");
        typeToName.put(1111, "OTHER");
        typeToName.put(7, "REAL");
        typeToName.put(2006, "REF");
        typeToName.put(5, "SMALLINT");
        typeToName.put(2002, "STRUCT");
        typeToName.put(92, "TIME");
        typeToName.put(93, "TIMESTAMP");
        typeToName.put(-6, "TINYINT");
        typeToName.put(-3, "VARBINARY");
        typeToName.put(12, "VARCHAR");
        nameToType = new HashMap();
        nameToType.put("ARRAY", 2003);
        nameToType.put("BIGINT", -5);
        nameToType.put("BINARY", -2);
        nameToType.put("BIT", -7);
        nameToType.put("BLOB", 2004);
        nameToType.put("BOOLEAN", 16);
        nameToType.put("CHAR", 1);
        nameToType.put("CLOB", 2005);
        nameToType.put("DATALINK", 70);
        nameToType.put("DATE", 91);
        nameToType.put("DECIMAL", 3);
        nameToType.put("DISTINCT", 2001);
        nameToType.put("DOUBLE", 8);
        nameToType.put("FLOAT", 6);
        nameToType.put("INTEGER", 4);
        nameToType.put("JAVA_OBJECT", 2000);
        nameToType.put("LONGVARBINARY", -4);
        nameToType.put("LONGVARCHAR", -1);
        nameToType.put("NCHAR", -15);
        nameToType.put("NCLOB", 2011);
        nameToType.put("NVARCHAR", -9);
        nameToType.put("LONGNVARCHAR", -16);
        nameToType.put("NULL", 0);
        nameToType.put("NUMERIC", 2);
        nameToType.put("OTHER", 1111);
        nameToType.put("REAL", 7);
        nameToType.put("REF", 2006);
        nameToType.put("SMALLINT", 5);
        nameToType.put("STRUCT", 2002);
        nameToType.put("TIME", 92);
        nameToType.put("TIMESTAMP", 93);
        nameToType.put("TINYINT", -6);
        nameToType.put("VARBINARY", -3);
        nameToType.put("VARCHAR", 12);
    }
}