package com.dquav.dquav_platform.mapper;

import com.dquav.dquav_platform.entity.UserLevel;

/**
 * @author TrEx
 * @date 2021/3/24 - 11:08
 */
public interface UserLevelMapper {

    /**
     * 添加用户等级数据
     * @param userLevel 用户等级数据
     * @return 受影响行数
     */
    Integer addUserLevel(UserLevel userLevel);

    /**
     * 查询用户等级
     * @return 返回用户等级数据
     */
    UserLevel getUserLevel();

    /**
     * 删除对应用户等级
     * @param lid 用户等级id
     * @return 返回受影响行数
     */
    Integer deleteUserLevelByLid(Integer lid);


}
