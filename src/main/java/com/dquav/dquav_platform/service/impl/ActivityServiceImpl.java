package com.dquav.dquav_platform.service.impl;

import com.dquav.dquav_platform.entity.*;
import com.dquav.dquav_platform.mapper.*;
import com.dquav.dquav_platform.service.IActivityService;
import com.dquav.dquav_platform.service.ex.*;
import com.dquav.dquav_platform.util.UserLevelLimitUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    @Resource
    DocMapper docMapper;
    @Resource
    GuestPhotoMapper guestPhotoMapper;
    @Resource
    PhotoPackageMapper photoPackageMapper;

    /**
     * 添加活动项目
     *
     * @param username 用户名
     * @param activity 活动项目数据
     * @throws InsertException 抛出插入数据异常
     */
    @Override
    public void addActivity(String username, Activity activity) throws UserNotFoundException,
            UserLevelLimitFailException, InsertException {
        UserList user = userListMapper.getUserListByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("用户未登录");
        }
//        查询用户等级

        Integer userLid = user.getLid();
        UserLevelLimitUtil userLevelLimitUtil = new UserLevelLimitUtil();
        userLevelLimitUtil.userLimit(userLid);


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
    public Activity findActivityById(Integer activityId) throws ActivityNotFoundException {
        Activity activity = activityMapper.getByActivityId(activityId);
        if (activity == null) {
            throw new ActivityNotFoundException("活动内容已删除");
        }
        return activity;
    }

    @Override
    public void changeActivity(String oldActivityName, String username, String activityName, Date activityStartTime,
                               Date activityEndTime, String activityAdds) throws UserNotFoundException,
            ActivityNotFoundException, UserLevelLimitFailException, UpdateException {
        UserList user = userListMapper.getUserListByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("请登录后操作");
        }
//        查询用户等级
        Integer userLid = user.getLid();
        UserLevelLimitUtil userLevelLimitUtil = new UserLevelLimitUtil();
        userLevelLimitUtil.userLimit(userLid);

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
    public void removeActivity(String username,String activityName) throws ActivityNotFoundException, ActivityDeleteFailException {
        UserList user = userListMapper.getUserListByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("请登录后操作");
        }
//        查询用户等级
        Integer userLid = user.getLid();
        UserLevelLimitUtil userLevelLimitUtil = new UserLevelLimitUtil();
        userLevelLimitUtil.userLimit(userLid);


        Activity activity = getActivity(activityName);
        if (activity == null) {
            throw new ActivityNotFoundException("未找到活动");
        }
        Integer activityId = activity.getActivityId();

        //删除所有子表
        List<Doc> docList = docMapper.getDocNameByActivityId(activityId);
        if (!docList.isEmpty()) {
            Integer docResult = docList.size();
            Integer docRows = docMapper.deleteDocByActivityId(activityId);
            if (!docRows.equals(docResult)) {
                throw new DocDeleteFailException("删除活动下文档失败");
            }
        }
        List<GuestPhoto> photoList = guestPhotoMapper.getPhotoByActivityId(activityId);
        if (!photoList.isEmpty()) {
            Integer guestResult = photoList.size();
            Integer guestRows = guestPhotoMapper.deletePhotoById(activityId);
            if (!guestRows.equals(guestResult)) {
                throw new PhotoDeleteFailException("删除活动下照片失败");
            }
        }
        List<PhotoPackage> photoPackageList = photoPackageMapper.getPhotoPackageByActivityId(activityId);
        if (!photoPackageList.isEmpty()) {
            Integer packageResult = photoPackageList.size();
            Integer packageRows = photoPackageMapper.deletePhotoPackageById(activityId);
            if (!packageRows.equals(packageResult)) {
                throw new PackageDeleteFailException("删除活动下压缩包失败");
            }
        }

        Integer rows = activityMapper.deleteByActivityId(activity.getActivityId());
        if (rows != 1) {
            throw new ActivityDeleteFailException("删除活动失败，请稍后重试");
        }
    }
}
