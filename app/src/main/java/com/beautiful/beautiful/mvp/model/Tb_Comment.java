package com.beautiful.beautiful.mvp.model;

import java.io.Serializable;
import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by Mr.R on 2016/9/24.
 */
public class Tb_Comment extends BmobObject implements Serializable{

    private String avatar = "";
    private String targetNickname = "";
    private String nickname = "";
    private String textContent = "";
    private String parentId = "";//评论目标视频的id
    private String childId = "";//某视频下某评论的id
    private Integer commentNum;
//    private List<Tb_Comment> childComments;
//    private long datetime;


    public Tb_Comment() {
        this.commentNum = 0;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTargetNickname() {
        return targetNickname;
    }

    public void setTargetNickname(String targetNickname) {
        this.targetNickname = targetNickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }
}
