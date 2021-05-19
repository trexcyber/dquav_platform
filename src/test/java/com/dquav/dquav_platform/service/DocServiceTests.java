package com.dquav.dquav_platform.service;

import com.dquav.dquav_platform.entity.Doc;
import com.dquav.dquav_platform.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author TrEx
 * @date 2021/5/19 - 14:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DocServiceTests {

    @Resource
    IDocService iDocService;

    @Test
    public void saveDoc(){
        try {
            String username = "trexcyber";
            Doc doc = new Doc();
            doc.setActivityId(2);
            doc.setDocName("文档测试1123");
            doc.setDocSite("c://doc/123.docx");
            iDocService.saveDoc(username,doc);
            System.out.println("ok");
        }catch (ServiceException e){
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void findDocByName(){
        try {
            String docName ="文档测试3";
            Doc doc =iDocService.findDocByName(docName);
            System.out.println(doc);
        }catch (ServiceException e){
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void findDocListByActivityId(){
        try {
            Integer activityId=2;
            List<Doc> docList =iDocService.findDocListByActivityId(activityId);
            System.out.println(docList);
        }catch (ServiceException e ){
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void removeDoc(){
        try {
            String username = "trexcyber";
            String docName ="文档测试1123";
            iDocService.removeDoc(username,docName);
        }catch (ServiceException e ){
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void removeDocByActivityName(){
        try {
            String username = "trexcyber";
            String activityName ="陕投三会(测试2)";
            iDocService.removeDocByActivityName(username,activityName);
        }catch (ServiceException e ){
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
        }
    }


}
