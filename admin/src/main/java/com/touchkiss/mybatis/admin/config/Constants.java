package com.touchkiss.mybatis.admin.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created on 2019/7/25 17:25
 *
 * @author Touchkiss
 */
public class Constants {
    public final static DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public final static DateFormat DEFAULT_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public final static DateFormat DEFAULT_TIME_FORMAT = new SimpleDateFormat("hh:mm:ss");
}
