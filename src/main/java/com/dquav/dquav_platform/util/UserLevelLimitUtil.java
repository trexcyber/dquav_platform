package com.dquav.dquav_platform.util;

import com.dquav.dquav_platform.mapper.UserLevelMapper;
import com.dquav.dquav_platform.mapper.UserListMapper;
import com.dquav.dquav_platform.service.ex.UserLevelLimitFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author TrEx
 * @date 2021/5/18 - 17:36
 */
@Component
public class UserLevelLimitUtil {


    @Resource
    private UserListMapper userListMapper;

    @Resource
    private UserLevelMapper userLevelMapper;

    private String levelNameUser(Integer uid) {
        Map<String, String> user = userListMapper.getUserLevelByUid(uid);
        return String.valueOf(user.get("levelName"));
    }

    private String levelNameList(Integer lid) {
        return userLevelMapper.findUserLevel(lid).getLevelName();
    }

    public void userLimit(Integer uid) {
        Map<String, String> user1 = userListMapper.getUserLevelByUid(uid);
        String levelName = String.valueOf(user1.get("levelName"));
        String lName1 = userLevelMapper.findUserLevel(1).getLevelName();
        String lName2 = userLevelMapper.findUserLevel(2).getLevelName();
        if (!levelName.equals(lName1) && !levelName.equals(lName2)) {
            throw new UserLevelLimitFailException("用户等级限制");
        }
    }

}
