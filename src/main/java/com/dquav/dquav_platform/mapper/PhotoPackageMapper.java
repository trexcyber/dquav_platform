package com.dquav.dquav_platform.mapper;

import com.dquav.dquav_platform.entity.PhotoPackage;

import java.util.List;

/**
 * 活动照片压缩包的持久层接口
 * @author TrEx
 * @date 2021/3/24 - 10:50
 */
public interface PhotoPackageMapper {


    /**
     * 插入照片包数据
     * @param photoPackage 照片包数据
     * @return 受影响行数
     */
    Integer addPhotoPackage(PhotoPackage photoPackage);

    /**
     * 查询照片包名称 根据活动项目id
     * @param activityId 活动项目id
     * @return 返回照片包数据
     */
    List<PhotoPackage> getPhotoPackageByActivityId(Integer activityId);

    /**
     * 查询照片包
     * @param photoId 压缩包id
     * @return 返回压缩包数据
     */
    PhotoPackage getPhotoPackageByPhotoId(Integer photoId);

    /**
     * 根据照片包名称 删除对应照片包数据
     * @param photoId 照片包id
     * @return 返回受影响行数
     */
    Integer deletePhotoPackageByPid(Integer photoId);

    /**
     * 根据活动项目id 删除对应的照片压缩包数据
     * @param activityId 活动项目id
     * @return 返回受影响行数
     */
    Integer deletePhotoPackageById(Integer activityId);

}
