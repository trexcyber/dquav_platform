package com.dquav.dquav_platform.entity;

import java.util.Date;

/**
 * @author TrEx
 * @date 2021/4/20 - 11:06
 */
public class BaseActivity {

    private String activityName;
    private Date activityStartTime;

    @Override
    public String toString() {
        return "BaseActivity{" +
                "activityName='" + activityName + '\'' +
                ", activityStartTime=" + activityStartTime +
                '}';
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Date getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(Date activityStartTime) {
        this.activityStartTime = activityStartTime;
    }
}
