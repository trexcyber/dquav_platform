package com.dquav.dquav_platform.entity;

/**
 * @author TrEx
 * @date 2021/3/23 - 10:43
 *
 * 活动照片压缩包的实体类
 */
public class PhotoPackage {

    private Integer photoId;
    private Integer activityId;
    private String photoPackageName;
    private String photoPackageSite;
    private Integer isGuest;

    @Override
    public String toString() {
        return "PhotoPackage{" +
                "photoId=" + photoId +
                ", activityId=" + activityId +
                ", photoPackageName='" + photoPackageName + '\'' +
                ", photoPackageSite='" + photoPackageSite + '\'' +
                ", isGuest=" + isGuest +
                '}';
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getPhotoPackageName() {
        return photoPackageName;
    }

    public void setPhotoPackageName(String photoPackageName) {
        this.photoPackageName = photoPackageName;
    }

    public String getPhotoPackageSite() {
        return photoPackageSite;
    }

    public void setPhotoPackageSite(String photoPackageSite) {
        this.photoPackageSite = photoPackageSite;
    }

    public Integer getIsGuest() {
        return isGuest;
    }

    public void setIsGuest(Integer isGuest) {
        this.isGuest = isGuest;
    }
}
