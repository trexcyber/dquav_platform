package com.dquav.dquav_platform.entity;

import java.util.Date;

/**
 * @author TrEx
 * @date 2021/3/23 - 10:20
 * 活动项目的实体类
 */

public class Activity {

    private Integer activityId;
    private String activityName;
    private Date activityStartTime;
    private Date activityEndTime;
    private String activityAdds;
    private Integer isDelete;
    /**
     * 活动创建时间
     */
    private Date createdTime;
    /**
     * 活动最近更新人
     * 活动最近更新时间
     */
    private String updateBy;
    private Date updateTime;

    @Override
    public String toString() {
        return "Activity{" +
                "activityId=" + activityId +
                ", activityName='" + activityName + '\'' +
                ", activityStartTime=" + activityStartTime +
                ", activityEndTime=" + activityEndTime +
                ", activityAdds='" + activityAdds + '\'' +
                ", isDelete=" + isDelete +
                ", createdTime=" + createdTime +
                ", updatedBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
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

    public Date getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(Date activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public String getActivityAdds() {
        return activityAdds;
    }

    public void setActivityAdds(String activityAdds) {
        this.activityAdds = activityAdds;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updatedBy) {
        this.updateBy = updatedBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
