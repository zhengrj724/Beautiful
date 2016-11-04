package com.beautiful.beautiful.mvp.model;

import java.io.Serializable;
import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by Mr.R on 2016/9/24.
 */
public class Tb_Comment extends BmobObject implements Serializable{

    private int _id;
    private String targetUserName;
    private String username;
    private String textContent;
    private long datetime;
    private int parentId;//评论目标视频的id
    private int childId;//某视频下某评论的id
    private List<Tb_Comment> childComments;

    public Tb_Comment() {}

    public Tb_Comment(int _id, String username, String textContent,
                      Long datetime, int parentId, int childId) {
        this._id = _id;
        this.username = username;
        this.textContent = textContent;
        this.datetime = datetime;
        this.parentId = parentId;
        this.childId = childId;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTargetUserName() {
        return targetUserName;
    }

    public void setTargetUserName(String targetUserName) {
        this.targetUserName = targetUserName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    public List<Tb_Comment> getChildComments() {
        return childComments;
    }

    public void setChildComments(List<Tb_Comment> childComments) {
        this.childComments = childComments;
    }
}
