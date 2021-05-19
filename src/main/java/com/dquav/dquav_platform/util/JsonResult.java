package com.dquav.dquav_platform.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.dquav.dquav_platform.entity.Doc;
import com.dquav.dquav_platform.entity.UserList;
import com.dquav.dquav_platform.mapper.DocMapper;
import org.apache.catalina.User;

import javax.annotation.Resource;

/**
 * @author TrEx
 * @date 2021/4/25 - 14:33
 *
 * Json格式转换的工具类
 */
public class JsonResult {

    /**
     * doc实体类Json忽略字段的工具
     * @param doc 文件实体类
     * @return JSON转换后的实体类
     */
    public String docJsonFilter(Doc doc){

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("activityId");
        filter.getExcludes().add("docSite");
        filter.getExcludes().add("isDelete");

        return JSON.toJSONString(doc, filter);
    }

    /**
     * 用户数据实体类Json忽略字段的工具
     * @param userList 用户数据
     * @return JSON转换后的实体类
     */
    public String userListJsonFilter(UserList userList){
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("password");

        return JSON.toJSONString(userList,filter);
    }
}
