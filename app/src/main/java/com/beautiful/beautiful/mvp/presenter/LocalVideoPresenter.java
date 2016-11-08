package com.beautiful.beautiful.mvp.presenter;

import com.beautiful.beautiful.mvp.Info.ILocalVideoView;
import com.beautiful.beautiful.mvp.ui.activity.LocalVideoActivity;
import com.beautiful.beautiful.utils.ToastUtil;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by krkj on 2016/11/8.
 */

public class LocalVideoPresenter {
    private ILocalVideoView view;

    public LocalVideoPresenter(ILocalVideoView view) {
        this.view = view;
    }

    //上传文件
    public void uploadFile(String path) {
        final BmobFile bmobFile = new BmobFile(new File(path));
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onProgress(Integer value) {
                ToastUtil.ShortToast((LocalVideoActivity)view,"进度"+value+"%");
            }

            @Override
            public void done(BmobException e) {
                ToastUtil.ShortToast((LocalVideoActivity)view,"完成"+bmobFile.getFileUrl());
            }

            @Override
            public void doneError(int code, String msg) {
                super.doneError(code, msg);
            }
        });
    }
}
