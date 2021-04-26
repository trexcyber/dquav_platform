package com.dquav.dquav_platform.mapper;

import com.dquav.dquav_platform.entity.Activity;
import com.dquav.dquav_platform.entity.BaseActivity;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author TrEx
 * @date 2021/4/19 - 15:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityMapperTests {

    @Resource
    ActivityMapper activityMapper;

    @Test
    public void addActivity(){
        Activity activity = new Activity();
        activity.setActivityName("陕投三会");
        Date date =new Date();
        activity.setActivityStartTime(date);
        activity.setActivityEndTime(date);
        activity.setIsDelete(0);
        activity.setCreatedTime(date);
        activity.setUpdateBy("trex");
        activity.setUpdateTime(date);
        Integer row =activityMapper.addActivity(activity);
        System.out.println(row);
    }

    @Test
    public void getByActivityName(){
        Activity activity = activityMapper.getByActivityName("陕投三会");
        System.out.println(activity);
    }

    @Test
    public void updateActivity(){
        Date date = new Date();
        Integer row = activityMapper.updateActivity(1,"陕西投资集团全国企业会",date,date,"皇冠假日酒店","trex",date);
        System.out.println(row);
    }

    /**
     * 循环遍历
     */
    @Test
    public void getActivityNameAndTime(){
        List<BaseActivity> list = activityMapper.getActivityNameAndTime();
        System.out.println("begin");
        for (BaseActivity baseActivity : list) {
            System.out.println(baseActivity);
        }
        System.out.println("end");
    }
}
