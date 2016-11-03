package com.beautiful.beautiful.mvp.ui.holder;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.beautiful.beautiful.R;
import com.beautiful.beautiful.utils.VideoUtil;
import com.bumptech.glide.Glide;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;


/**
 * Created by Mr.R on 2016/7/12.
 */
public class VideoHolder extends RecyclerView.ViewHolder {
    private Fragment fragment;
    public CardView cvVideo;
    public ImageView ivBackground;
    public CheckBox cbBrowse;
    public CheckBox cbComment;
    public CheckBox cbDuration;
    public TextView tvTheme;

    private VideoUtil videoUtil;

    public VideoHolder(final View parent,
                       Fragment fragment,
                       CardView cvVideo,
                       ImageView ivBackground,
                       CheckBox cbBrowse,
                       CheckBox cbComment,
                       CheckBox cbDuration,
                       TextView tvTheme) {
        super(parent);
        this.fragment = fragment;
        this.cvVideo = cvVideo;
        this.ivBackground = ivBackground;
        this.cbBrowse = cbBrowse;
        this.cbComment = cbComment;
        this.cbDuration = cbDuration;
        this.tvTheme = tvTheme;
        videoUtil = VideoUtil.getInstance();
    }

    public static VideoHolder newInstance(Fragment fragment,View parent) {
        CardView cvVideo = (CardView) parent.findViewById(R.id.cv_video);
        ImageView ivBackground = (ImageView) parent.findViewById(R.id.iv_background);
        CheckBox cbJoin = (CheckBox) parent.findViewById(R.id.cb_browse);
        CheckBox cbComment = (CheckBox) parent.findViewById(R.id.cb_comment);
        CheckBox cbLike = (CheckBox) parent.findViewById(R.id.cb_duration);
        TextView tvTheme = (TextView) parent.findViewById(R.id.tv_theme);

        return new VideoHolder(parent,fragment,cvVideo,ivBackground,
                cbComment,cbJoin,cbLike,tvTheme);
    }

    //设置图片
    public void setImageView(String url) {
        Glide.with(fragment).load(url).into(ivBackground);
    }

    public void setCbBrowse(CharSequence text) {
        cbBrowse.setText(text);
    }

    public void setCbComment(CharSequence text) {
        cbComment.setText(text);
    }

    public void setCbDuration(CharSequence text) {
        cbDuration.setText(text);
    }

    public void setTvTheme(CharSequence text) {
        tvTheme.setText(text);
    }

}
