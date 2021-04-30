package com.dquav.dquav_platform.service.impl;

import com.dquav.dquav_platform.entity.Activity;
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
     * @param username 用户名
     * @param activity 活动项目数据
     * @throws InsertException 抛出插入数据异常
     */
    @Override
    public void addActivity(String username ,Activity activity) throws UserNotFoundException, InsertException {
        UserList user = userListMapper.getUserListByUsername(username);
        if (user == null){
            throw new UserNotFoundException("用户未登录");
        }
        Date date = new Date();
        activity.setIsDelete(0);
        activity.setCreatedTime(date);
        activity.setUpdateBy(user.getName());
        activity.setUpdateTime(date);
    }

    @Override
    public List<Activity> findAllActivity() throws ActivityListNotFoundException {
        return null;
    }

    @Override
    public Activity getActivity(String activityName) throws ActivityNotFoundException {
        return null;
    }

    @Override
    public void changeActivity(Activity activity) throws ActivityNotFoundException, UpdateException {

    }

    @Override
    public void removeActivity(Integer activityId) throws ActivityNotFoundException, ActivityDeleteFailException {

    }
}
