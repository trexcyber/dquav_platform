package com.dquav.dquav_platform.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author TrEx
 * @date 2021/4/29 - 17:19
 * 转换时间格式的工具
 */
public class DateThisFormat {

    private static DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    /**
     * 日期格式转换
     * @param date 输入Date格式日期
     * @return 格式转换后的Date类型日期
     */
    public static Date dateTheFormat(Date date) {

        return stringToDate(dateToString(date));

    }

    /**
     * 日期 字符串格式转Date格式
     * @param dateStr 字符串格式日期
     * @return Date格式日期
     */
    private static Date stringToDate(String dateStr){

        Date date = null;
        try {
            date=dateFormat.parse(dateStr);
        }catch (Exception e ){
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Date格式转字符串
     * @param date Date格式日期
     * @return 字符串格式日期
     */
    private static String dateToString(Date date){
        try {
            return dateFormat.format(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }




}
