package com.dquav.dquav_platform.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author TrEx
 * @date 2021/4/20 - 11:06
 */
public class BaseActivity {

    private Integer activityId;
    private String activityName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date activityStartTime;

    @Override
    public String toString() {
        return "BaseActivity{" +
                "activityId=" + activityId +
                ", activityName='" + activityName + '\'' +
                ", activityStartTime=" + activityStartTime +
                '}';
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
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
