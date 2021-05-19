package com.dquav.dquav_platform.util;

import com.dquav.dquav_platform.mapper.UserLevelMapper;
import com.dquav.dquav_platform.mapper.UserListMapper;
import com.dquav.dquav_platform.service.ex.UserLevelLimitFailException;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author TrEx
 * @date 2021/5/18 - 17:36
 */
public class UserLevelLimitUtil {


    private UserListMapper userListMapper;

    private UserLevelMapper userLevelMapper;

    private String levelNameUser(Integer uid){
        Map<String, String> user = userListMapper.getUserLevelByUid(uid);
        return String.valueOf(user.get("levelName"));
    }

    private String levelNameList(Integer lid){
        return userLevelMapper.findUserLevel(lid).getLevelName();
    }

    public void userLimit(Integer uid) {
        UserLevelLimitUtil userLevelLimitUtil = new UserLevelLimitUtil();
        String lName1 = userLevelLimitUtil.levelNameList(1);
        String lName2 = userLevelLimitUtil.levelNameList(2);
        String uName1 = userLevelLimitUtil.levelNameUser(uid);
        if (!uName1.equals(lName1) || !uName1.equals(lName2)) {
            throw new UserLevelLimitFailException("用户等级限制");
        }
    }

}
