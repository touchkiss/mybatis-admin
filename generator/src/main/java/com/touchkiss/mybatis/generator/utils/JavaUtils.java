package com.touchkiss.mybatis.generator.utils;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import org.apache.commons.lang3.StringUtils;

public class JavaUtils {
    public JavaUtils() {
    }

    public static String toCamelCase(String value, boolean firstUpper) {
        value = value.toLowerCase();
        String[] a = value.split("_");
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < a.length; ++i) {
            if (i == 0 && !firstUpper) {
                sb.append(a[i].toLowerCase());
            } else {
                if (a[i].length() > 0) {
                    sb.append(a[i].substring(0, 1).toUpperCase());
                }

                if (a[i].length() > 1) {
                    sb.append(a[i].substring(1));
                }
            }
        }

        return sb.toString();
    }

    public static String getClassName(String packageName) {
        if (StringUtils.isNotBlank(packageName)) {
            int i = packageName.lastIndexOf(".");
            if (i != -1) {
                return packageName.substring(i + 1);
            }
        }

        return packageName;
    }
}
