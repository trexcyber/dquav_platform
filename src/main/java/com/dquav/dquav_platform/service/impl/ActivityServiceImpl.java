package com.dquav.dquav_platform.service.impl;

import com.dquav.dquav_platform.entity.Activity;
import com.dquav.dquav_platform.entity.BaseActivity;
import com.dquav.dquav_platform.entity.UserList;
import com.dquav.dquav_platform.mapper.ActivityMapper;
import com.dquav.dquav_platform.mapper.UserListMapper;
import com.dquav.dquav_platform.service.IActivityService;
import com.dquav.dquav_platform.service.ex.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author TrEx
 * @date 2021/4/28 - 17:16
 */
@Service
public class ActivityServiceImpl implements IActivityService {

    @Resource
    ActivityMapper activityMapper;
    @Resource
    UserListMapper userListMapper;

    /**
     * 添加活动项目
     *
     * @param username 用户名
     * @param activity 活动项目数据
     * @throws InsertException 抛出插入数据异常
     */
    @Override
    public void addActivity(String username, Activity activity) throws UserNotFoundException, InsertException {
        UserList user = userListMapper.getUserListByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("用户未登录");
        }
        Date date = new Date();
        activity.setIsDelete(0);
        activity.setCreatedTime(date);
        activity.setUpdateBy(user.getName());
        activity.setUpdateTime(date);

//        处理传入的活动开始、结束时间参数


        activity.setActivityName(activity.getActivityName());
        activity.setActivityStartTime(activity.getActivityStartTime());
        activity.setActivityStartTime(activity.getActivityEndTime());
        activity.setActivityAdds(activity.getActivityAdds());

        Integer rows = activityMapper.addActivity(activity);

        if (rows != 1) {
            throw new InsertException("添加活动项目异常，请稍后再试");
        }

    }

    @Override
    public List<BaseActivity> findAllActivity() throws ActivityListNotFoundException {
        List<BaseActivity> baseActivityList = activityMapper.getActivityNameAndTime();
        if (baseActivityList == null) {
            throw new ActivityListNotFoundException("未创建任何活动项目");
        }
        return activityMapper.getActivityNameAndTime();
    }

    @Override
    public Activity getActivity(String activityName) throws ActivityNotFoundException {
        Activity activity = activityMapper.getByActivityName(activityName);
        if (activity == null) {
            throw new ActivityNotFoundException("活动内容已删除");
        }
        return activity;
    }

    @Override
    public void changeActivity(String oldActivityName, String username, String activityName, Date activityStartTime,
                               Date activityEndTime, String activityAdds) throws UserNotFoundException,
            ActivityNotFoundException, UpdateException {
        UserList user = userListMapper.getUserListByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("请登录后操作");
        }
        Activity oldActivity = activityMapper.getByActivityName(oldActivityName);
        if (oldActivity == null) {
            throw new ActivityNotFoundException("活动内容已删除");
        }
        String name = user.getName();
        Date date = new Date();
        Integer activityId = oldActivity.getActivityId();
        Integer rows = activityMapper.updateActivity(activityId, activityName, activityStartTime, activityEndTime,
                activityAdds, name, date);
        if (rows != 1) {
            throw new UpdateException("更新信息异常");
        }
    }

    @Override
    public void removeActivity(String activityName) throws ActivityNotFoundException, ActivityDeleteFailException {
        Activity activity = getActivity(activityName);
        if (activity == null) {
            throw new ActivityNotFoundException("未找到活动");
        }
        //删除所有子表

        Integer rows = activityMapper.deleteByActivityId(activity.getActivityId());
        if (rows != 1 ){
            throw new ActivityDeleteFailException("删除活动失败，请稍后重试");
        }
    }
}
