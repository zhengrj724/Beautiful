package com.beautiful.beautiful.utils;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Mr.R on 2016/9/25.
 */
public class VideoUtil {

    private static VideoUtil instance;

    private VideoUtil() {}

    public static VideoUtil getInstance() {
        if (instance == null) {
            synchronized (VideoUtil.class) {
                if (instance == null) {
                    instance = new VideoUtil();
                }
            }
        }
        return instance;
    }

    public void createVideoThumbnail(final Fragment fragment,
                                      final ImageView imageView,
                                      final String mVideoUrl) {
        Observable<Bitmap> observable = Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                int kind = MediaStore.Video.Thumbnails.MINI_KIND;

                retriever.setDataSource(mVideoUrl);
                Bitmap bitmap = retriever.getFrameAtTime();
                subscriber.onNext(bitmap);
                retriever.release();
            }
        });

        observable.observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        //设置封面
                        Log.i("VideoUtil", "call: VideoUtil");
                        Glide.with(fragment)
                                .load(bitmap)
                                .centerCrop()
                                .into(imageView);
//                        imageView.setImageBitmap(bitmap);
                    }
                });
    }
}
