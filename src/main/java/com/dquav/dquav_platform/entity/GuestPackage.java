package com.dquav.dquav_platform.entity;

/**
 * @author TrEx
 * @date 2021/3/23 - 11:03
 *
 * 客户照片压缩包的实体类
 */
public class GuestPackage {

    private Integer guestPid;
    private Integer activityId;
    private String guestPackageName;
    private String guestPackageAdds;

    @Override
    public String toString() {
        return "GuestPackage{" +
                "guestPid=" + guestPid +
                ", activityId=" + activityId +
                ", guestPackageName='" + guestPackageName + '\'' +
                ", guestPackageAdds='" + guestPackageAdds + '\'' +
                '}';
    }

    public Integer getGuestPid() {
        return guestPid;
    }

    public void setGuestPid(Integer guestPid) {
        this.guestPid = guestPid;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getGuestPackageName() {
        return guestPackageName;
    }

    public void setGuestPackageName(String guestPackageName) {
        this.guestPackageName = guestPackageName;
    }

    public String getGuestPackageAdds() {
        return guestPackageAdds;
    }

    public void setGuestPackageAdds(String guestPackageAdds) {
        this.guestPackageAdds = guestPackageAdds;
    }
}
