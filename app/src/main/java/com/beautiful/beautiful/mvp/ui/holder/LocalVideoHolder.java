package com.beautiful.beautiful.mvp.ui.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beautiful.beautiful.R;
import com.beautiful.beautiful.utils.VideoUtil;
import com.bumptech.glide.Glide;

/**
 * Created by Mr.R on 2016/7/12.
 */
public class LocalVideoHolder extends RecyclerView.ViewHolder {
    public RelativeLayout llLocalVideo;
    public ImageView ivThumbnail;
    public TextView tvTitle;
    public TextView tvDuration;

    public LocalVideoHolder(final View parent,
                            RelativeLayout llLocalVideo,
                            ImageView ivThumbnail,
                            TextView tvTitle,
                            TextView tvDuration) {
        super(parent);
        this.llLocalVideo = llLocalVideo;
        this.ivThumbnail = ivThumbnail;
        this.tvTitle = tvTitle;
        this.tvDuration = tvDuration;
    }

    public static LocalVideoHolder newInstance(View parent) {
        RelativeLayout llLocalVideo = (RelativeLayout) parent.findViewById(R.id.ll_local_video);
        ImageView ivThumbnail = (ImageView) parent.findViewById(R.id.iv_thumbnail);
        TextView tvTitle = (TextView) parent.findViewById(R.id.tv_title);
        TextView tvDuration = (TextView) parent.findViewById(R.id.tv_duration);

        return new LocalVideoHolder(parent,llLocalVideo,ivThumbnail,tvTitle,tvDuration);
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
}
