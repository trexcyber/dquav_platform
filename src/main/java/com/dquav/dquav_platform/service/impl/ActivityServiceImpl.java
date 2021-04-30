package com.dquav.dquav_platform.service.impl;

import com.dquav.dquav_platform.entity.Activity;
import com.dquav.dquav_platform.mapper.ActivityMapper;
import com.dquav.dquav_platform.service.IActivityService;
import com.dquav.dquav_platform.service.ex.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author TrEx
 * @date 2021/4/28 - 17:16
 */
@Service
public class ActivityServiceImpl implements IActivityService {

    @Resource
    ActivityMapper activityMapper;

    /**
     * 添加活动项目
     * @param activity 活动项目数据
     * @throws InsertException 抛出插入数据异常
     */
    @Override
    public void addActivity(Activity activity) throws InsertException {
        
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
