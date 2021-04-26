package com.dquav.dquav_platform.entity;

/**
 * @author TrEx
 * @date 2021/3/23 - 10:51
 *
 * 内部人员用户的实体类
 */
public class UserList {

    private Integer uid;
    private Integer lid;
    private String username;
    private String password;
    /**
     * 用户真实姓名
     * 用户预留联系电话
     */
    private String name;
    private String telephone;

    @Override
    public String toString() {
        return "UserList{" +
                "uid=" + uid +
                ", lid=" + lid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
