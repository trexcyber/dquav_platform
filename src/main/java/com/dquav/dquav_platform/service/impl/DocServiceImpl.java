package com.dquav.dquav_platform.service.impl;

import com.dquav.dquav_platform.entity.Activity;
import com.dquav.dquav_platform.entity.Doc;
import com.dquav.dquav_platform.entity.UserList;
import com.dquav.dquav_platform.mapper.ActivityMapper;
import com.dquav.dquav_platform.mapper.DocMapper;
import com.dquav.dquav_platform.mapper.UserListMapper;
import com.dquav.dquav_platform.service.IDocService;
import com.dquav.dquav_platform.service.ex.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author TrEx
 * @date 2021/5/8 - 11:06
 */
@Service
public class DocServiceImpl implements IDocService {

    @Resource
    DocMapper docMapper;
    @Resource
    ActivityMapper activityMapper;
    @Resource
    UserListMapper userListMapper;

    @Override
    public void saveDoc(String username, Doc doc) throws UserNotFoundException, InsertException {
        UserList userResult = userListMapper.getUserListByUsername(username);
        if (userResult == null) {
            throw new UserNotFoundException("未登录用户");
        }

        Doc docResult = docMapper.getDocByName(doc.getDocName());
        if (docResult != null){
            throw new InsertException("文件名已存在");
        }
        Integer rows = docMapper.addDoc(doc);
        if (rows != 1) {
            throw new InsertException("文档上传失败");
        }


    }

    @Override
    public Doc findDocByName(String docName) throws DocNotFoundException {
        Doc doc = docMapper.getDocByName(docName);
        if (doc == null) {
            throw new DocNotFoundException("未找到文档");
        }
        return doc;
    }

    @Override
    public Doc findDocByActivityIdAndDocName(Integer activityId, String docName) throws ActivityNotFoundException, DocNotFoundException {
        Activity activity =activityMapper.getByActivityId(activityId);
        if (activity == null){
            throw new ActivityNotFoundException("未找到活动");
        }
        Doc doc = docMapper.getDocByActivityId(activityId,docName);
        if (doc ==null){
            throw new DocNotFoundException("活动下该文档资源丢失！");
        }
        return doc;
    }

    @Override
    public List<Doc> findDocListByActivityId(Integer activityId) throws ActivityNotFoundException,
            DocListNotFoundException {

        Activity activity =activityMapper.getByActivityId(activityId);
        if (activity == null){
            throw new ActivityNotFoundException("未找到活动");
        }
        return docMapper.getDocNameByActivityId(activityId);
    }

    @Override
    public void removeDoc(String username, String docName) throws UserNotFoundException, DocNotFoundException,
            DocDeleteFailException {
        UserList userResult = userListMapper.getUserListByUsername(username);
        if (userResult == null) {
            throw new UserNotFoundException("未登录用户");
        }
        Doc doc = docMapper.getDocByName(docName);
        if (doc == null) {
            throw new DocNotFoundException("未找到文档");
        }
        Integer docId = doc.getDocId();
        Integer rows = docMapper.deleteDocById(docId);
        if (rows != 1) {
            throw new DocDeleteFailException("文档删除失败");
        }
    }

    @Override
    public void removeDocByActivityName(String username, String activityName) throws UserNotFoundException,
            ActivityNotFoundException, DocListNotFoundException, DocDeleteFailException {
        UserList userResult = userListMapper.getUserListByUsername(username);
        if (userResult == null) {
            throw new UserNotFoundException("未登录用户");
        }
        Activity activityResult = activityMapper.getByActivityName(activityName);
        if (activityResult == null) {
            throw new ActivityNotFoundException("活动项目已删除");
        }
        Integer activityId = activityResult.getActivityId();
        List<Doc> docs = docMapper.getDocNameByActivityId(activityId);
        if (docs == null) {
            throw new DocNotFoundException("活动下未找到文档");
        }

        Integer rows = docMapper.deleteDocByActivityId(activityId);
        if (rows == 0) {
            throw new DocDeleteFailException("活动下文档删除失败");
        }
    }
}
