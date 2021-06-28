package com.dquav.dquav_platform.service.impl;

import com.dquav.dquav_platform.entity.Activity;
import com.dquav.dquav_platform.entity.GuestPhoto;
import com.dquav.dquav_platform.entity.UserLevel;
import com.dquav.dquav_platform.entity.UserList;
import com.dquav.dquav_platform.mapper.ActivityMapper;
import com.dquav.dquav_platform.mapper.GuestPhotoMapper;
import com.dquav.dquav_platform.mapper.UserLevelMapper;
import com.dquav.dquav_platform.mapper.UserListMapper;
import com.dquav.dquav_platform.service.IActivityService;
import com.dquav.dquav_platform.service.IGuestPhotoService;
import com.dquav.dquav_platform.service.IUserListService;
import com.dquav.dquav_platform.service.ex.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author TrEx
 * @date 2021/5/14 - 10:03
 */
@Service
public class GuestServiceImpl implements IGuestPhotoService {

    @Resource
    UserListMapper userListMapper;
    @Resource
    ActivityMapper activityMapper;
    @Resource
    GuestPhotoMapper guestPhotoMapper;

    @Override
    public void savePhoto(Integer uid, String activityName, String photoAdds) throws UserNotFoundException,
            ActivityNotFoundException,
            InsertException {
        UserList user = userListMapper.getUserListById(uid);
        if (user == null) {
            throw new UserNotFoundException("用户未找到异常！");
        }
        Activity activity = activityMapper.getByActivityName(activityName);
        if (activity == null) {
            throw new ActivityNotFoundException("活动未找到！");
        }
        Integer activityId = activity.getActivityId();
        GuestPhoto photo = new GuestPhoto();
        photo.setActivityId(activityId);
        photo.setPhotoAdds(photoAdds);
        Integer rows = guestPhotoMapper.addPhoto(photo);
        if (rows != 1) {
            throw new InsertException("添加相片失败!");
        }

    }

    @Override
    public List<GuestPhoto> findPhotoByActivityName(String activityName) throws ActivityNotFoundException {
        Activity activity = activityMapper.getByActivityName(activityName);
        if (activity == null){
            throw new ActivityNotFoundException("活动未找到异常");
        }
        Integer activityId = activity.getActivityId();
        return guestPhotoMapper.getPhotoByActivityId(activityId);
    }

    @Override
    public void deletePhotoByPid(Integer uid, Integer pid) throws UserNotFoundException, PhotoNotFoundException,
            PhotoDeleteFailException {
        UserList userList = userListMapper.getUserListById(uid);
        if (userList == null){
            throw new UserNotFoundException("用户未找到");
        }
        GuestPhoto result = guestPhotoMapper.getPhotoByPid(pid);
        if (result == null){
            throw new PhotoNotFoundException("照片未找到");
        }
        Integer rows = guestPhotoMapper.deletePhotoByPid(pid);
        if (rows != 1){
            throw new PhotoDeleteFailException("照片删除失败！");
        }
    }
}
