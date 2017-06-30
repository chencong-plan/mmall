package com.mmall.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * 项目：  mmall
 * 包名：  com.mmall.util
 * 作者：  chencong
 * 时间：  2017/6/28 19:12.
 * 描述：  时间工具类
 */
public class DateTimeUtil {

    //joda--time

    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 自定义格式字符串转换成时间
     *
     * @param dateTimeStr 时间字符串
     * @param formatStr   自定义格式
     * @return 返回时间Date
     */
    public static Date strToDate(String dateTimeStr, String formatStr) {
        DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern(formatStr);
        DateTime dateTime = dateTimeFormat.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    /**
     * 自定义格式时间转换字符串
     *
     * @param date      时间date
     * @param formatStr 自定义格式
     * @return 返回时间字符串
     */
    public static String dateToStr(Date date, String formatStr) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(formatStr);
    }

    /**
     * 字符串转化成时间 格式为yyyy-MM-dd HH:mm:ss
     *
     * @param dateTimeStr 将要转化的时间字符串
     * @return 返回时间Date
     */
    public static Date strToDate(String dateTimeStr) {
        DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime = dateTimeFormat.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    /**
     * 时间转化成字符串  格式为yyyy-MM-dd HH:mm:ss
     *
     * @param date 指定时间Date
     * @return 返回默认格式时间字符串
     */
    public static String dateToStr(Date date) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANDARD_FORMAT);
    }
}
