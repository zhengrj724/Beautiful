package com.beautiful.beautiful.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;

/**
 * Created by Mr.R on 2016/7/10.
 */
public class BaseApplication extends Application {
//    private Tb_user user;
//    private UserInfo myInfo;
    private List<Activity> activities;

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    //初始化
    private void init() {
        activities = new ArrayList<>();
        //提供以下两种方式进行初始化操作：

        //第一：默认初始化
        Bmob.initialize(this, "919709dec2e4304b9ab2fad60e3393a5");

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、
        //文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        //BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        //.setApplicationId("Your Application ID")
        ////请求超时时间（单位为秒）：默认15s
        //.setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        //.setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        //.setFileExpiration(2500)
        //.build();
        //Bmob.initialize(config);
    }

    public void finish() {
        for (Activity activity : activities) {
            activity.finish();
        }
    }
}
