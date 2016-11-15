package com.beautiful.beautiful.mvp.model;

import java.io.File;
import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Mr.R on 2016/9/24.
 */
public class Tb_Video extends BmobObject implements Serializable{
    private String video;//视频地址
    private String image;//视频封面
    private String theme;//主题
    private String describe;//详情描述
    private Integer playNum;//播放次数
    private Integer commentNum;//评论次数
    private String duration;//时长
    private String type;//视频类型
    private Integer isHot;//是否热门

    public Tb_Video() {
        this.playNum = 0;
        this.commentNum = 0;
        this.isHot = 0;
    }

    public Tb_Video(String video, String image, String theme,
                    String describe, String duration, String type) {
        this.video = video;
        this.image = image;
        this.theme = theme;
        this.describe = describe;
        this.duration = duration;
        this.type = type;
        this.playNum = 0;
        this.commentNum = 0;
        this.isHot = 0;
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

    public int getIsHot() {
        return isHot;
    }

    public void setIsHot(int isHot) {
        this.isHot = isHot;
    }
}
