package com.beautiful.beautiful.mvp.presenter;

import android.os.Handler;
import android.util.Log;

import com.beautiful.beautiful.mvp.Info.IVideoView;
import com.beautiful.beautiful.mvp.model.entity.Tb_Video;
import com.beautiful.beautiful.utils.HandlerKey;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import rx.Observable;

/**
 * Created by Mr.R on 2016/9/25.
 */
public class VideoPresenter {

    private IVideoView view;
    private BmobQuery<Tb_Video> query;


    public VideoPresenter(IVideoView view) {
        this.view = view;
        query = new BmobQuery<>();
    }

    //刷新所有视频
    public void refreshVideo(final List<Tb_Video> videos, final Handler handler) {
        query.setLimit(10);
        query.findObjects(new FindListener<Tb_Video>() {
            @Override
            public void done(List<Tb_Video> list, BmobException e) {
                if (e == null) {
                    //查询成功
                    videos.addAll(list);
                    handler.sendEmptyMessage(HandlerKey.REFRESH_SUCCESS);
                } else {
                    e.printStackTrace();
                    handler.sendEmptyMessage(HandlerKey.REFRESH_FAIL);
                }
            }
        });
    }

    //下拉刷新
    public void doRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    view.doRefresh();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
