package com.dquav.dquav_platform.service;

import com.dquav.dquav_platform.entity.Activity;
import com.dquav.dquav_platform.service.ex.*;

import java.util.List;

/**
 * @author TrEx
 * @date 2021/4/27 - 11:08
 *
 * 处理活动项目的业务层接口
 */
public interface IActivityService {

    /**
     * 添加活动
     * @param activity 活动项目数据
     * @throws InsertException 抛出插入数据异常
     */
    void addActivity(Activity activity) throws InsertException;

    /**
     * 查询所有活动
     * @return 返回所有活动名称和开始时间
     * @throws ActivityListNotFoundException 抛出未找到活动异常
     */
    List<Activity> findAllActivity() throws ActivityListNotFoundException;

    /**
     * 查询活动项目信息
     * @param activityName 活动项目名称
     * @return 返回活动项目的数据信息
     * @throws ActivityNotFoundException 抛出未找到活动异常
     */
    Activity getActivity(String activityName) throws ActivityNotFoundException;

    /**
     * 修改活动项目信息
     * @param activity 活动信息
     * @throws ActivityNotFoundException 未找到活动项目异常
     * @throws UpdateException 抛出更新异常
     */
    void changeActivity(Activity activity) throws ActivityNotFoundException,UpdateException;

    /**
     * 根据活动id 删除活动项目
     * @param activityId 活动项目id
     * @throws ActivityNotFoundException 抛出未找到活动项目异常
     * @throws ActivityDeleteFailException 抛出活动删除失败异常
     */
    void removeActivity(Integer activityId) throws ActivityNotFoundException,ActivityDeleteFailException;



}
