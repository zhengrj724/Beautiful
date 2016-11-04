package com.beautiful.beautiful.mvp.model;

import cn.bmob.v3.BmobUser;

/**
 * Created by Mr.R on 2016/9/25.
 */
public class Tb_User extends BmobUser {

    private String username;
    private String password;
    private String avatar;//头像
    private String nickName;//昵称
    private long collectNum;//收藏数
    private long myUploadNum;//上传数
    private long myBrowsedNum;//浏览数

    public Tb_User() {}

    public Tb_User(String username, String password) {
        this.username = username;
        this.password = password;
        this.nickName = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
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

    public long getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(long collectNum) {
        this.collectNum = collectNum;
    }

    public long getMyUploadNum() {
        return myUploadNum;
    }

    public void setMyUploadNum(long myUploadNum) {
        this.myUploadNum = myUploadNum;
    }

    public long getMyBrowsedNum() {
        return myBrowsedNum;
    }

    public void setMyBrowsedNum(long myBrowsedNum) {
        this.myBrowsedNum = myBrowsedNum;
    }
}
