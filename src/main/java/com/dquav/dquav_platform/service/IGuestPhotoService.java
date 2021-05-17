package com.dquav.dquav_platform.service;

import com.dquav.dquav_platform.entity.GuestPhoto;
import com.dquav.dquav_platform.service.ex.*;

import java.util.List;

/**
 * @author TrEx
 * @date 2021/5/12 - 15:48
 */
public interface IGuestPhotoService {

    /**
     * 保存上传图片
     *
     * @param uid          用户id
     * @param activityName 活动项目id
     * @param photoAdds    活动保存路径
     * @throws UserNotFoundException 用户未找到异常
     * @throws ActivityNotFoundException 活动未找到异常
     * @throws InsertException       保存操作异常
     */
    void savePhoto(Integer uid, String activityName, String photoAdds) throws UserNotFoundException,
            ActivityNotFoundException,
            InsertException;

    /**
     * 根据活动项目id 查询所有照片数据
     *
     * @param activityName 活动项目名字
     * @return 所有照片数据
     * @throws ActivityNotFoundException 未找到活动异常
     */
    List<GuestPhoto> findPhotoByActivityName(String activityName) throws ActivityNotFoundException;

    /**
     * 根据pid 删除照片数据
     *
     * @param uid 用户id
     * @param pid 照片pid
     * @throws UserNotFoundException    用户未找到异常
     * @throws PhotoNotFoundException   未找到需删除照片异常
     * @throws PhotoDeleteFailException 照片删除异常
     */
    void deletePhotoByPid(Integer uid, Integer pid) throws UserNotFoundException,
            PhotoNotFoundException, PhotoDeleteFailException;


}
