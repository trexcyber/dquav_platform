package com.dquav.dquav_platform.entity;

/**
 * @author TrEx
 * @date 2021/3/23 - 10:59
 *
 * 内部用户等级表
 */
public class UserLevel {

    private Integer lid;
    private String levelName;

    @Override
    public String toString() {
        return "UserLevel{" +
                "lid=" + lid +
                ", levelName='" + levelName + '\'' +
                '}';
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
}
