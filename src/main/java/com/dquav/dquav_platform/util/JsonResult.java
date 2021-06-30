package com.dquav.dquav_platform.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.dquav.dquav_platform.entity.Doc;
import com.dquav.dquav_platform.entity.UserList;
import com.dquav.dquav_platform.mapper.DocMapper;
import org.apache.catalina.User;

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
     * @param userList 用户数据
     * @return JSON转换后的实体类
     */
    public String userListJsonFilter(UserList userList) {
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("password");

        return JSON.toJSONString(userList, filter);
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
