package com.beautiful.beautiful.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Mr.R on 2016/7/22.
 */
public class QiniuCloudUtil {

    /**
     * 下载
     * @param url:下载地址图片
     * @param handler
     */
    public static void download(final String url, final Handler handler) {

        new Thread() {
            @Override
            public void run() {
                Bitmap bitmap;
                try {
                    //下载资源
                    URL u = new URL(url);
                    InputStream inputStream = u.openStream();
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    //将资源发给主线程去更新UI
                    Message msg = Message.obtain();
                    msg.what = HandlerKey.DOWNLOAD_SUCCESS;
                    msg.obj = bitmap;
                    handler.sendMessage(msg);
                    inputStream.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
