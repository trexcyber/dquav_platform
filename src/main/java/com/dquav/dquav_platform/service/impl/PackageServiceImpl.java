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
import com.dquav.dquav_platform.util.UserLevelLimitUtil;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author TrEx
 * @date 2021/5/14 - 11:06
 */
@Service
public class PackageServiceImpl implements IPackageService {

    @Resource
    UserListMapper userListMapper;
    @Resource
    ActivityMapper activityMapper;
    @Resource
    PhotoPackageMapper photoPackageMapper;
    @Resource
    UserLevelLimitUtil userLevelLimitUtil;

    @Override
    public void savePackage(Integer uid,PhotoPackage photoPackage) throws UserNotFoundException
            , ActivityNotFoundException, InsertException ,PackageNullException{
        UserList result = userListMapper.getUserListById(uid);
        if (result == null) {
            throw new UserNotFoundException("用户未找到");
        }
        Activity activity = activityMapper.getByActivityId(photoPackage.getActivityId());
        if (activity == null) {
            throw new ActivityNotFoundException("活动未找到");
        }
        userLevelLimitUtil.userLimit(result.getUid());

        if (photoPackage.getPhotoPackageName()==null|"".equals(photoPackage.getPhotoPackageName())){
            throw new PackageNullException("上传压缩包信息不全");
        }
        if (photoPackage.getPhotoPackageSite()==null||"".equals(photoPackage.getPhotoPackageSite())){
            throw new PackageNullException("上传压缩包信息不全异常");
        }

        Integer rows = photoPackageMapper.addPhotoPackage(photoPackage);
        if (rows == null) {
            throw new InsertException("添加失败");
        }

    }

    @Override
    public PhotoPackage findPackageByActivityIdAndDocName(Integer activityId, String photoPackageName) throws ActivityNotFoundException, DocNotFoundException {

        if (activityMapper.getByActivityId(activityId) == null){
            throw new ActivityNotFoundException("未找到活动");
        }
        PhotoPackage photoPackage = photoPackageMapper.getPackageByActivityId(activityId,photoPackageName);
        if (photoPackage ==null){
            throw new DocNotFoundException("活动下该文档资源丢失！");
        }
        return photoPackage;
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
        userLevelLimitUtil.userLimit(userList.getUid());
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
        userLevelLimitUtil.userLimit(userList.getUid());
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



