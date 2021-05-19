package com.dquav.dquav_platform.service;

import com.dquav.dquav_platform.entity.Activity;
import com.dquav.dquav_platform.entity.BaseActivity;
import com.dquav.dquav_platform.entity.UserList;
import com.dquav.dquav_platform.service.ex.*;

import java.util.Date;
import java.util.List;

/**
 * @author TrEx
 * @date 2021/4/27 - 11:08
 * <p>
 * 处理活动项目的业务层接口
 */
public interface IActivityService {

    /**
     * 添加活动
     *
     * @param username 用户名
     * @param activity 活动项目数据
     * @throws UserNotFoundException 抛出用户未找到异常
     * @throws UserLevelLimitFailException 用户等级限制异常
     * @throws InsertException       抛出插入数据异常
     */
    void addActivity(String username, Activity activity) throws UserNotFoundException, UserLevelLimitFailException,
            InsertException;

    /**
     * 查询所有活动
     *
     * @return 返回所有活动名称和开始时间
     * @throws ActivityListNotFoundException 抛出未找到活动异常
     */
    List<BaseActivity> findAllActivity() throws ActivityListNotFoundException;

    /**
     * 查询活动项目信息
     *
     * @param activityName 活动项目名称
     * @return 返回活动项目的数据信息
     * @throws ActivityNotFoundException 抛出未找到活动异常
     */
    Activity getActivity(String activityName) throws ActivityNotFoundException;

    /**
     * 查询活动项目信息
     *
     * @param activityId 活动项目id
     * @return 返回活动项目的数据信息
     * @throws ActivityNotFoundException 未找到活动项目异常
     */
    Activity findActivityById(Integer activityId) throws ActivityNotFoundException;

    /**
     * 修改活动信息
     *
     * @param oldActivityName   原活动项目名
     * @param username          用户名
     * @param activityName      修改后活动项目名
     * @param activityStartTime 修改后活动开始时间
     * @param activityEndTime   修改后活动结束时间
     * @param activityAdds      修改后活动地址
     * @throws UserNotFoundException     未存在账户操作异常
     * @throws ActivityNotFoundException 抛出活动已删除异常
     * @throws UpdateException           抛出更新异常
     */
    void changeActivity(String oldActivityName, String username, String activityName, Date activityStartTime,
                        Date activityEndTime, String activityAdds) throws UserNotFoundException,
            ActivityNotFoundException, UpdateException;

    /**
     * 根据活动id 删除活动项目
     *
     * @param username 用户名
     * @param activityName 活动项目id
     * @throws ActivityNotFoundException   抛出未找到活动项目异常
     * @throws ActivityDeleteFailException 抛出活动删除失败异常
     */
    void removeActivity(String username ,String activityName) throws ActivityNotFoundException, ActivityDeleteFailException;


}
