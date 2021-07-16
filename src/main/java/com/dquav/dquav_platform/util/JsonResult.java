package com.dquav.dquav_platform.util;

import com.alibaba.fastjson.*;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.dquav.dquav_platform.entity.Doc;
import com.dquav.dquav_platform.entity.UserList;
import com.dquav.dquav_platform.mapper.DocMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.catalina.User;
import org.apache.commons.lang.StringEscapeUtils;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author TrEx
 * @date 2021/4/25 - 14:33
 * <p>
 * Json格式转换的工具类
 */
public class JsonResult {

    /**
     * doc实体类Json忽略字段的工具
     *
     * @param doc 文件实体类
     * @return JSON转换后的实体类
     */
    public String docJsonFilter(Doc doc) {

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("activityId");
        filter.getExcludes().add("docSite");
        filter.getExcludes().add("isDelete");

        return JSON.toJSONString(doc, filter);
    }

    /**
     * 用户数据实体类Json忽略字段的工具
     *
     * 如果为json字符串输出的话，在后端内输出时不会进入fastJson转义；
     * 但如果输出到前端接口时，fastJson会将json字符串二次转义，输出“\”;
     * 因此需要传输jsonObject对象，fastJson将不会二次转义，从而输出正确格式。
     *
     * 当fastJson收到不是对象，而是字符串格式 的json数据时，
     * 如果有"双引号之类特殊符号,由会自动加上反斜杠，
     * 也就是说，我的方法返回结果时，
     * 不应该是我画蛇添足式的手动做一个JSONObject.toJSONString方法调用转成json，
     * 而是直接返回一个定义好的对象，
     * 这样fastJson就会自动转换成正常格式的json串。
     *
     * @param userList 用户数据
     * @return JSON转换后的实体类
     */
    public JSONObject userListJsonFilter(UserList userList) {

        Object object =JSONObject.toJSON(userList);
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("password");

        System.out.println(object);
        String result =StringEscapeUtils.unescapeJavaScript(JSON.toJSONString(object,filter));
        //        JSON.toJSONString(object, filter)
        return JSON.parseObject(result);
    }

    public String userJsonFiler(){
        ObjectMapper objectMapper = new ObjectMapper();
       return null;
    }

    /**
     * 获取JSON字符串中的uid
     *
     * @param userList JSON字符串的userList类对象
     * @return 返回uid
     */
    public String getUserUid(String userList) {
        JSONObject userJson = JSON.parseObject(userList);
        return userJson.getString("uid");
    }

    /**
     * 获取userList对象中JSON字符串的username
     * @param userList JSON字符串的userList类对象
     * @return 返回username
     */
    public String getUsername(String userList) {
        JSONObject userJson = JSON.parseObject(userList);
        return userJson.getString("username");
    }

}
