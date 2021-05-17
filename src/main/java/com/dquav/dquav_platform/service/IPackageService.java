package com.dquav.dquav_platform.service;

import com.dquav.dquav_platform.entity.Doc;
import com.dquav.dquav_platform.entity.PhotoPackage;
import com.dquav.dquav_platform.service.ex.*;

import java.util.List;

/**
 * @author TrEx
 * @date 2021/5/12 - 16:51
 */
public interface IPackageService {

    /**
     * 保存压缩包
     *
     * @param uid          用户id
     * @param activityName 活动名
     * @param photoPackage 照片压缩包数据对象
     * @throws ActivityNotFoundException 活动未找到异常
     * @throws UserNotFoundException     用户未找到异常
     * @throws InsertException           添加压缩包异常
     */
    void savePackage(Integer uid, String activityName, PhotoPackage photoPackage) throws UserNotFoundException,
            ActivityNotFoundException, InsertException;

    /**
     * 根据活动id 获取所有压缩包数据信息
     *
     * @param activityId 活动id
     * @return 所有压缩包数据
     * @throws ActivityNotFoundException 活动未找到异常
     */
    List<PhotoPackage> findPackageByActivityId(Integer activityId) throws ActivityNotFoundException;

    /**
     * 根据压缩包id 获得压缩包数据信息
     *
     * @param photoId 压缩包id
     * @return 压缩包数据信息
     * @throws PackageNotFoundException  压缩包未找到异常
     * @throws ActivityNotFoundException 活动未找到异常
     */
    PhotoPackage findPackageByPhotoId(Integer photoId) throws PackageNotFoundException;

    /**
     * 根据压缩包id 删除压缩包数据
     * <p>
     * 根据用户等级判断是否执行操作
     *
     * @param uid     用户id
     * @param photoId 压缩包id
     * @throws UserNotFoundException      用户未找到异常
     * @throws PackageNotFoundException   压缩包未找到异常
     * @throws PackageDeleteFailException 压缩包删除失败异常
     */
    void removePackageByPhotoId(Integer uid, Integer photoId) throws UserNotFoundException,
            PackageNotFoundException, PackageDeleteFailException;

    /**
     * 根据活动id 删除所有压缩包数据
     *
     * @param uid        用户id
     * @param activityId 活动id
     * @throws UserNotFoundException     用户未找到异常
     * @throws ActivityNotFoundException 活动未找到异常
     * @throws PhotoDeleteFailException  照片删除异常
     */
    void removeAllPackageByActivityId(Integer uid, Integer activityId) throws UserNotFoundException,
            ActivityNotFoundException, PhotoDeleteFailException;

}
