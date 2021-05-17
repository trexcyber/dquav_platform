package com.dquav.dquav_platform.entity;

/**
 * @author TrEx
 * @date 2021/3/23 - 10:36
 *
 * 活动文档的实体类
 */
public class Doc {

    private Integer docId;
    private Integer activityId;
    private String docName;
    private String docSite;
    private String isDelete;

    @Override
    public String toString() {
        return "Doc{" +
                "docId=" + docId +
                ", activityId=" + activityId +
                ", docName='" + docName + '\'' +
                ", docSite='" + docSite + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocSite() {
        return docSite;
    }

    public void setDocSite(String docSite) {
        this.docSite = docSite;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }
}
