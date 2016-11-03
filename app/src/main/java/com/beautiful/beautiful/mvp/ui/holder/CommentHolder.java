package com.beautiful.beautiful.mvp.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beautiful.beautiful.R;
import com.beautiful.beautiful.lib.CircleImageView;


/**
 * Created by Mr.R on 2016/7/12.
 */
public class CommentHolder extends RecyclerView.ViewHolder {

    public RelativeLayout rlComment;
    public CircleImageView ivAvatar;
    public TextView tvName;
    public TextView tvContent;
    public TextView tvTime;
    public TextView tvCommentNum;

    public CommentHolder(final View parent,
                         RelativeLayout rlComment,
                         CircleImageView ivAvatar,
                         TextView tvName,
                         TextView tvContent,
                         TextView tvTime,
                         TextView tvCommentNum) {
        super(parent);
        this.rlComment = rlComment;
        this.ivAvatar = ivAvatar;
        this.tvName = tvName;
        this.tvContent = tvContent;
        this.tvTime = tvTime;
        this.tvCommentNum = tvCommentNum;
    }

    public static CommentHolder newInstance(View parent) {
        RelativeLayout rlComment = (RelativeLayout) parent.findViewById(R.id.rl_comment);
        CircleImageView ivAvatar = (CircleImageView) parent.findViewById(R.id.iv_avatar);
        TextView tvName  = (TextView) parent.findViewById(R.id.tv_name);
        TextView tvContent = (TextView) parent.findViewById(R.id.tv_content);
        TextView tvTime = (TextView) parent.findViewById(R.id.tv_time);
        TextView tvCommentNum = (TextView) parent.findViewById(R.id.tv_commentNum);

        return new CommentHolder(parent,rlComment, ivAvatar,
                tvName,tvContent,tvTime,tvCommentNum);
    }

    //设置图片
    public void setImageView(String imageUrl) {
//        Picasso.with(context).load(imageUrl).fit().centerCrop().into(imageView);
    }

    public void setTvName(CharSequence text) {
        tvName.setText(text);
    }

    public void setTvContent(CharSequence text) {
        tvContent.setText(text);
    }

    public void setTvTime(CharSequence text) {
        tvTime.setText(text);
    }

    public void setTvCommentNum(CharSequence text) {
        tvCommentNum.setText(text);
    }

}
