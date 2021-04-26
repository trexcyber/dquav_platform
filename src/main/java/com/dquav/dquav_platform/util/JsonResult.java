package com.dquav.dquav_platform.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.dquav.dquav_platform.entity.Doc;
import com.dquav.dquav_platform.mapper.DocMapper;

import javax.annotation.Resource;

/**
 * @author TrEx
 * @date 2021/4/25 - 14:33
 */
public class JsonResult {

    public String docJsonFilter(Doc doc){

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("activityId");
        filter.getExcludes().add("docSite");
        filter.getExcludes().add("idDelete");

        return JSON.toJSONString(doc, filter);
    }
}
