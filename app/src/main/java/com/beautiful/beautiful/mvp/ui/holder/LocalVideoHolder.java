package com.beautiful.beautiful.mvp.ui.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beautiful.beautiful.R;
import com.beautiful.beautiful.config.State;
import com.beautiful.beautiful.utils.ToastUtil;
import com.beautiful.beautiful.utils.VideoUtil;
import com.bumptech.glide.Glide;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by Mr.R on 2016/7/12.
 */
public class LocalVideoHolder extends RecyclerView.ViewHolder {
    private Context context;
    public LinearLayout llLocalVideo;
    public ImageView ivThumbnail;
    public TextView tvTitle;
    public TextView tvDuration;
    public Button btnUpload;
    public CircularProgressBar cpbUpload;
    private BmobFile bmobFile;

    public LocalVideoHolder(final View parent,
                            Context context,
                            LinearLayout llLocalVideo,
                            ImageView ivThumbnail,
                            TextView tvTitle,
                            TextView tvDuration,
                            Button btnUpload,
                            CircularProgressBar cpbUpload) {
        super(parent);
        this.context = context;
        this.llLocalVideo = llLocalVideo;
        this.ivThumbnail = ivThumbnail;
        this.tvTitle = tvTitle;
        this.tvDuration = tvDuration;
        this.btnUpload = btnUpload;
        this.cpbUpload = cpbUpload;
    }

    public static LocalVideoHolder newInstance(View parent,Context context) {
        LinearLayout llLocalVideo = (LinearLayout) parent.findViewById(R.id.ll_local_video);
        ImageView ivThumbnail = (ImageView) parent.findViewById(R.id.iv_thumbnail);
        TextView tvTitle = (TextView) parent.findViewById(R.id.tv_title);
        TextView tvDuration = (TextView) parent.findViewById(R.id.tv_duration);
        Button btnUpload = (Button) parent.findViewById(R.id.btn_upload);
        CircularProgressBar cpbUpload = (CircularProgressBar) parent.findViewById(R.id.cpb_upload);

        return new LocalVideoHolder(parent,context,llLocalVideo,
                ivThumbnail,tvTitle,tvDuration,btnUpload,cpbUpload);
    }

    //设置图片
    public void setImageView(Context context,String url) {
        Glide.with(context).load(url).into(ivThumbnail);
    }

    public void setTvTitle(CharSequence text) {
        tvTitle.setText(text);
    }

    public void setTvDuration(CharSequence text) {
        tvDuration.setText(text);
    }

    public void setButtonText(String text) {
        btnUpload.setText(text);
    }

    //获取按钮的状态
    public int getButtonState() {
        if (btnUpload.getText().toString().equals("上传")) {
            return State.CANCEL;
        }
        return State.UPLOADING;
    }

    //上传
    public void upload(String url) {
        btnUpload.setText("取消");
        showProgress();
        uploadFile(url);
    }

    //取消上传
    public void cancel() {
        btnUpload.setText("上传");
        hideProgress();
        bmobFile.cancel();
    }

    //显示进度条
    public void showProgress() {
        cpbUpload.setProgress(0);
        cpbUpload.setVisibility(View.VISIBLE);
    }

    //隐藏进度条
    public void hideProgress() {
        cpbUpload.setVisibility(View.INVISIBLE);
    }

    //更新进度
    public void updateProgress(int progress) {
        cpbUpload.setProgressWithAnimation(progress,300);
    }

    //上传文件
    public void uploadFile(String path) {
        bmobFile = new BmobFile(new File(path));
        bmobFile.upload(new UploadFileListener() {
            @Override
            public void onProgress(Integer value) {
                updateProgress(value);
            }

            @Override
            public void done(BmobException e) {
                setButtonText("上传");
                hideProgress();
                ToastUtil.ShortToast(context,"完成"+bmobFile.getFileUrl());
            }

            @Override
            public void doneError(int code, String msg) {
                super.doneError(code, msg);
                setButtonText("重试");
                hideProgress();
                ToastUtil.ShortToast(context,"上传失败"+msg);
            }
        });
    }
}
