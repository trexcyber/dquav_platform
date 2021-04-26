package com.dquav.dquav_platform.mapper;

import com.dquav.dquav_platform.entity.UserLevel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author TrEx
 * @date 2021/4/26 - 15:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserLevelMapperTests {

    @Resource
    UserLevelMapper userLevelMapper;

    @Test
    public void addUserLevel() {
        UserLevel userLevel = new UserLevel();
        userLevel.setLid(4);
        userLevel.setLevelName("领导");
        Integer rows = userLevelMapper.addUserLevel(userLevel);
        System.out.println(rows);
    }

    @Test
    public void getUserLevel() {
        List<UserLevel> userLevels = userLevelMapper.getUserLevel();
        for (UserLevel userLevel : userLevels) {
            System.out.println(userLevel);
        }
    }

    @Test
    public void deleteUserLevelByLid() {
        Integer lid = 4;
        Integer rows = userLevelMapper.deleteUserLevelByLid(lid);
        System.out.println(rows);
    }

}
