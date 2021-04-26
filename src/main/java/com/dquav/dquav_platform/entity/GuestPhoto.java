package com.dquav.dquav_platform.entity;

/**
 * @author TrEx
 * @date 2021/3/23 - 11:01
 *
 * 客户照片的实体类
 */
public class GuestPhoto {

    private Integer pid;
    private Integer activityId;
    private String photoAdds;

    @Override
    public String toString() {
        return "GuestPhoto{" +
                "pid=" + pid +
                ", activityId=" + activityId +
                ", photoAdds='" + photoAdds + '\'' +
                '}';
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getPhotoAdds() {
        return photoAdds;
    }

    public void setPhotoAdds(String photoAdds) {
        this.photoAdds = photoAdds;
    }
}

