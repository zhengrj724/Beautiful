package com.beautiful.beautiful.mvp.model.entity;

import java.io.File;
import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Mr.R on 2016/9/24.
 */
public class Tb_Video extends BmobObject implements Serializable{
    private String video;
    private String image;
    private String theme;
    private String describe;
    private Integer playNum;
    private Integer commentNum;
    private String duration;
    private String type;

    public Tb_Video() {

    }

    public Tb_Video(String video, String image, String theme, String describe,
                    int playNum, int commentNum, String duration, String type) {
        this.video = video;
        this.image = image;
        this.theme = theme;
        this.describe = describe;
        this.playNum = playNum;
        this.commentNum = commentNum;
        this.duration = duration;
        this.type = type;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getBrowseNum() {
        return playNum;
    }

    public void setBrowseNum(int browseNum) {
        this.playNum = browseNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
