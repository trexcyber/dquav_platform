package com.dquav.dquav_platform.service;

import com.dquav.dquav_platform.entity.Doc;
import com.dquav.dquav_platform.mapper.DocMapper;
import com.dquav.dquav_platform.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Priority;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author TrEx
 * @date 2021/4/27 - 16:29
 * <p>
 * 处理文档的业务层接口
 */
@Service
public interface IDocService {

    /**
     * 添加文档
     *
     * @param username 用户名
     * @param doc 文档对象
     * @throws UserNotFoundException 用户未登录异常
     * @throws InsertException       添加异常
     */
    void saveDoc(String username,Doc doc) throws UserNotFoundException, InsertException;

    /**
     * 根据文档名 查询文档
     *
     * @param docName 文档名
     * @return 文档数据信息
     * @throws DocNotFoundException 文档未找到异常
     */
    Doc findDocByName(String docName) throws DocNotFoundException;

    /**
     * 根据活动id 获取当前活动下所有文档名称
     *
     * @param activityId 活动id
     * @return 所有文档名称
     * @throws ActivityNotFoundException 活动不存在异常
     * @throws DocListNotFoundException  未找到文档异常
     */
    List<Doc> findDocListByActivityId(Integer activityId) throws ActivityNotFoundException, DocListNotFoundException;

    /**
     * 删除文档
     *
     * @param username 用户名
     * @param docName 文档名
     * @throws UserNotFoundException  用户未登录异常
     * @throws DocNotFoundException   文档未找到异常
     * @throws DocDeleteFailException 文档删除失败异常
     */
    void removeDoc(String username,String docName) throws UserNotFoundException, DocNotFoundException, DocDeleteFailException;

    /**
     * 删除活动下所有文档
     *
     * @param username 用户名
     * @param activityName 活动名
     * @throws UserNotFoundException 用户未登录异常
     * @throws ActivityNotFoundException 未找到活动异常
     * @throws DocListNotFoundException 列表中未查询到文档异常
     * @throws DocDeleteFailException 文档删除失败异常
     */
    void removeDocByActivityName(String username,String activityName) throws UserNotFoundException,ActivityNotFoundException,DocListNotFoundException,DocDeleteFailException;

}
