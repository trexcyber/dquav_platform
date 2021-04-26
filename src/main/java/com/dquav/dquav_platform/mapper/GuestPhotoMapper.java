package com.dquav.dquav_platform.mapper;

import com.dquav.dquav_platform.entity.GuestPhoto;

import java.util.List;

/**
 * 照片数据的持久层接口
 * @author TrEx
 * @date 2021/3/24 - 14:17
 */
public interface GuestPhotoMapper {

    /**
     * 添加客户照片
     * @param photo 照片数据
     * @return 受影响行数
     */
    Integer addPhoto(GuestPhoto photo);

    /**
     * 查询活动项目下的照片数据
     * @param activityId 活动id
     * @return 照片数据
     */
    List<GuestPhoto> getPhotoByActivityId(Integer activityId);

    /**
     * 删除照片
     * @param pid 照片id
     * @return 受影响行数
     */
    Integer deletePhotoByPid(Integer pid);

    /**
     * 根据活动项目id 删除照片
     * @param activityId 活动项目id
     * @return 受影响行数
     */
    Integer deletePhotoById(Integer activityId);

}
