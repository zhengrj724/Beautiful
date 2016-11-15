package com.beautiful.beautiful.mvp.ui.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beautiful.beautiful.R;
import com.beautiful.beautiful.config.State;
import com.beautiful.beautiful.mvp.model.Tb_Video;
import com.beautiful.beautiful.utils.ToastUtil;
import com.bumptech.glide.Glide;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadBatchListener;
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
    public TextView tvSize;
    public Button btnUpload;
    public CircularProgressBar cpbUpload;
    private BmobFile bmobFile;
    private String duration;

    public LocalVideoHolder(final View parent,
                            Context context,
                            LinearLayout llLocalVideo,
                            ImageView ivThumbnail,
                            TextView tvTitle,
                            TextView tvDuration,
                            TextView tvSize,
                            Button btnUpload,
                            CircularProgressBar cpbUpload) {
        super(parent);
        this.context = context;
        this.llLocalVideo = llLocalVideo;
        this.ivThumbnail = ivThumbnail;
        this.tvTitle = tvTitle;
        this.tvDuration = tvDuration;
        this.tvSize = tvSize;
        this.btnUpload = btnUpload;
        this.cpbUpload = cpbUpload;
    }

    public static LocalVideoHolder newInstance(View parent,Context context) {
        LinearLayout llLocalVideo = (LinearLayout) parent.findViewById(R.id.ll_local_video);
        ImageView ivThumbnail = (ImageView) parent.findViewById(R.id.iv_thumbnail);
        TextView tvTitle = (TextView) parent.findViewById(R.id.tv_title);
        TextView tvDuration = (TextView) parent.findViewById(R.id.tv_duration);
        TextView tvSize = (TextView) parent.findViewById(R.id.tv_size);
        Button btnUpload = (Button) parent.findViewById(R.id.btn_upload);
        CircularProgressBar cpbUpload = (CircularProgressBar) parent.findViewById(R.id.cpb_upload);

        return new LocalVideoHolder(parent,context,llLocalVideo,
                ivThumbnail,tvTitle,tvDuration,tvSize,btnUpload,cpbUpload);
    }

    //设置图片
    public void setImageView(Context context,String url) {
        Glide.with(context).load(url).into(ivThumbnail);
    }

    public void setTvTitle(CharSequence text) {
        tvTitle.setText(text);
    }

    public void setTvDuration(long duration) {
        this.duration = getDuration(duration);
        tvDuration.setText("时长："+this.duration);
    }

    private String getDuration(long duration) {
        int s = (int) (duration / 1000);
        int min = s / 60;
        String text = "";
        if (min == 0) {
            if (s < 10) {
                text = text+"00:0"+s;
            } else {
                text = text+"00:"+s;
            }
        } else {
            if (s < 10) {
                text = text+min+":0"+s;
            } else {
                text = text+min+":"+s;
            }
        }
        return text;
    }

    public void setTvSize(float size) {
        BigDecimal bd = new BigDecimal((size/1024/1024));
        float sizeMB = bd.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
        tvSize.setText("大小："+sizeMB+"MB");
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
    public void upload(String url,String theme,
                       String describe, String type) {
        btnUpload.setText("取消");
        showProgress();
        uploadFile(url,theme,describe,type);
    }

    //取消上传
    public void cancel() {
        btnUpload.setText("上传");
        hideProgress();
        bmobFile.cancel();
    }

    //显示进度条
    private void showProgress() {
        cpbUpload.setProgress(0);
        cpbUpload.setVisibility(View.VISIBLE);
    }

    //隐藏进度条
    private void hideProgress() {
        cpbUpload.setVisibility(View.INVISIBLE);
    }

    //更新进度
    private void updateProgress(int progress) {
        cpbUpload.setProgressWithAnimation(progress,300);
    }

    //上传文件
    private void uploadFile(final String path, final String theme,
                            final String describe, final String type) {
        bmobFile = new BmobFile(new File(path));
        bmobFile.upload(new UploadFileListener() {
            @Override
            public void onProgress(Integer value) {
                updateProgress(value);
            }

            @Override
            public void done(BmobException e) {
                saveFile(path,theme,describe,type);
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

    private void saveFile(String videoUrl,String theme,
                          String describe,String type) {
        Tb_Video video = new Tb_Video();
        video.setVideo(videoUrl);
        video.setTheme(theme);
        video.setDescribe(describe);
        video.setDuration(this.duration);
        video.setType(type);

        video.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    setButtonText("上传");
                    hideProgress();
                    ToastUtil.ShortToast(context,"完成"+bmobFile.getFileUrl());
                } else {
                    setButtonText("重试");
                    hideProgress();
                    ToastUtil.ShortToast(context,"上传失败"+e.getMessage());
                    Log.e("imageFile",e.getMessage());
                }
            }
        });
    }
}
