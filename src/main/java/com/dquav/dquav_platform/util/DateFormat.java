package com.dquav.dquav_platform.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author TrEx
 * @date 2021/4/29 - 17:19
 * 转换时间格式的工具
 */
public class DateFormat {

    /**
     * 日期转字符串
     * @param date Date类型的时间数据
     * @return 字符串类型的时间数据
     */
    public static String dateToStr(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return simpleDateFormat.format(date);
    }

    /**
     * 字符串转日期
     * @param str 字符串类型的时间数据
     * @return Date类型的时间数据
     */
    public static Date strToDate(String str){
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = simpleDateFormat.parse(str);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return date;
    }
}
