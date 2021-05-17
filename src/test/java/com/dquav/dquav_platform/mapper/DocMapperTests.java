package com.dquav.dquav_platform.mapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.dquav.dquav_platform.entity.Doc;
import com.dquav.dquav_platform.util.JsonResult;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author TrEx
 * @date 2021/4/20 - 11:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DocMapperTests {

    @Resource
    DocMapper docMapper;

    @Test
    public void addDoc() {
        Doc doc = new Doc();
        doc.setActivityId(1);
        doc.setDocName("活动方案（内部）");
        doc.setDocSite("c://doc/活动方案、修改");
        doc.setIsDelete("");
        Integer rows = docMapper.addDoc(doc);
        System.out.println(rows);
    }

    @Test
    public void getDocByName() {
        String docName = "活动方案（内部）";
        Doc doc = docMapper.getDocByName(docName);
        System.out.println(doc);

    }


    @Test
    public void getDocNameByActivityId() {
        Integer activityId = 1;
        List<Doc> doc = docMapper.getDocNameByActivityId(activityId);
        JsonResult jsonResult = new JsonResult();
        System.out.println("begin");
        for (Doc doc1 : doc) {
            System.out.println(jsonResult.docJsonFilter(doc1));
        }
        System.out.println("end");
    }

    @Test
    public void deleteDocById() {
        Integer docId = 2;
        Integer rows = docMapper.deleteDocById(docId);
        System.out.println(rows);
    }

    @Test
    public void deleteDocByActivityId() {
        Integer activityId = 1;
        Integer rows = docMapper.deleteDocByActivityId(activityId);
        System.out.println(rows);
    }
}