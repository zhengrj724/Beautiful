package com.beautiful.beautiful.mvp.model.entity;

import cn.bmob.v3.BmobUser;

/**
 * Created by Mr.R on 2016/9/25.
 */
public class Tb_User extends BmobUser {

    private String avatar;
    private String nickName;
    private boolean sex;
    private int age;

    public Tb_User() {

    }

    public Tb_User(String avatar, String nickName, boolean sex, int age) {
        this.avatar = avatar;
        this.nickName = nickName;
        this.sex = sex;
        this.age = age;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
