package com.dquav.dquav_platform.service.impl;

import com.dquav.dquav_platform.entity.Activity;
import com.dquav.dquav_platform.entity.Doc;
import com.dquav.dquav_platform.entity.PhotoPackage;
import com.dquav.dquav_platform.entity.UserList;
import com.dquav.dquav_platform.mapper.ActivityMapper;
import com.dquav.dquav_platform.mapper.PhotoPackageMapper;
import com.dquav.dquav_platform.mapper.UserListMapper;
import com.dquav.dquav_platform.service.IPackageService;
import com.dquav.dquav_platform.service.ex.*;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author TrEx
 * @date 2021/5/14 - 11:06
 */
public class PackageServiceImpl implements IPackageService {

    @Resource
    UserListMapper userListMapper;
    @Resource
    ActivityMapper activityMapper;
    @Resource
    PhotoPackageMapper photoPackageMapper;

    @Override
    public void savePackage(Integer uid, String activityName, PhotoPackage photoPackage) throws UserNotFoundException
            , ActivityNotFoundException, InsertException {
        UserList result = userListMapper.getUserListById(uid);
        if (result == null) {
            throw new UserNotFoundException("用户未找到");
        }
        Activity activity = activityMapper.getByActivityName(activityName);
        if (activity == null) {
            throw new ActivityNotFoundException("活动未找到");
        }
        Integer rows = photoPackageMapper.addPhotoPackage(photoPackage);
        if (rows == null) {
            throw new InsertException("添加失败");
        }

    }

    @Override
    public List<PhotoPackage> findPackageByActivityName(String activityName) throws ActivityNotFoundException {
        Activity result = activityMapper.getByActivityName(activityName);
        if (result == null) {
            throw new ActivityNotFoundException("未找到活动");
        }
        Integer activityId = result.getActivityId();
        return photoPackageMapper.getPhotoPackageByActivityId(activityId);
    }

    @Override
    public PhotoPackage findPackageByPhotoPackageName(String photoPackageName) throws PackageNotFoundException {
        PhotoPackage photoPackage = photoPackageMapper.getPhotoPackageByPhotoName(photoPackageName);
        if (photoPackage == null) {
            throw new PackageNotFoundException("压缩包未找到");
        }
        return photoPackage;
    }

    @Override
    public void removePackageByPhotoPackageName(Integer uid, String photoPackageName) throws UserNotFoundException,
            PackageNotFoundException, PackageDeleteFailException {
        UserList userList = userListMapper.getUserListById(uid);
        if (userList == null) {
            throw new UserNotFoundException("未登录用户，请登陆后重试");
        }
        PhotoPackage photoPackage = photoPackageMapper.getPhotoPackageByPhotoName(photoPackageName);
        if (photoPackage == null) {
            throw new PackageNotFoundException("未找到照片压缩包");
        }
        Integer photoId = photoPackage.getPhotoId();
        Integer rows = photoPackageMapper.deletePhotoPackageByPid(photoId);
        if (rows != 1) {
            throw new PackageNotFoundException("压缩包删除失败");
        }
    }

    @Override
    public void removeAllPackageByActivityId(Integer uid, String activityName) throws UserNotFoundException,
            ActivityNotFoundException, PhotoDeleteFailException {
        UserList userList = userListMapper.getUserListById(uid);
        if (userList == null) {
            throw new UserNotFoundException("未找到用户");
        }
        Activity activity = activityMapper.getByActivityName(activityName);
        if (activity == null) {
            throw new ActivityNotFoundException("未找到活动");
        }
        Integer activityId = activity.getActivityId();
        Integer rows = photoPackageMapper.deletePhotoPackageById(activityId);
        if (rows == 0) {
            throw new PhotoDeleteFailException("压缩包删除失败");
        }
    }
}



