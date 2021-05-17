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
    public List<PhotoPackage> findPackageByActivityId(Integer activityId) throws ActivityNotFoundException {
        Activity result = activityMapper.getByActivityId(activityId);
        if (result == null) {
            throw new ActivityNotFoundException("未找到活动");
        }
        return photoPackageMapper.getPhotoPackageByActivityId(activityId);
    }

    @Override
    public PhotoPackage findPackageByPhotoId(Integer photoId) throws PackageNotFoundException {
        PhotoPackage photoPackage = photoPackageMapper.getPhotoPackageByPhotoId(photoId);
        if (photoPackage == null) {
            throw new PackageNotFoundException("压缩包未找到");
        }
        return photoPackage;
    }

    @Override
    public void removePackageByPhotoId(Integer uid, Integer photoId) throws UserNotFoundException,
            PackageNotFoundException, PackageDeleteFailException {

    }

    @Override
    public void removeAllPackageByActivityId(Integer uid, Integer activityId) throws UserNotFoundException,
            ActivityNotFoundException, PhotoDeleteFailException {

    }
}


