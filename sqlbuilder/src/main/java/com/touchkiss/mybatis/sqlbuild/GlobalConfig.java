package com.touchkiss.mybatis.sqlbuild;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

public class GlobalConfig {
    public GlobalConfig() {
    }

    public static boolean isUseQuotes() {
        String GLOBAL_USE_QUOTES = System.getProperty("com.touchkiss.mybatis.sqlbuild.global.useQuotes", "false");
        return StringUtils.isNotEmpty(GLOBAL_USE_QUOTES) && GLOBAL_USE_QUOTES.equalsIgnoreCase("true");
    }
}
