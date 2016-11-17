package com.beautiful.beautiful.mvp.ui.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beautiful.beautiful.R;
import com.beautiful.beautiful.lib.CircleImageView;
import com.bumptech.glide.Glide;


/**
 * Created by Mr.R on 2016/7/12.
 */
public class CommentHolder extends RecyclerView.ViewHolder {

    private Context context;
    public RelativeLayout rlComment;
    public CircleImageView ivAvatar;
    public TextView tvName;
    public TextView tvContent;
    public TextView tvTime;
    public RadioButton rbAnswer;

    public CommentHolder(final View parent,
                         Context context,
                         RelativeLayout rlComment,
                         CircleImageView ivAvatar,
                         TextView tvName,
                         TextView tvContent,
                         TextView tvTime,
                         RadioButton rbAnswer) {
        super(parent);
        this.context = context;
        this.rlComment = rlComment;
        this.ivAvatar = ivAvatar;
        this.tvName = tvName;
        this.tvContent = tvContent;
        this.tvTime = tvTime;
        this.rbAnswer = rbAnswer;
    }

    public static CommentHolder newInstance(Context context,View parent) {
        RelativeLayout rlComment = (RelativeLayout) parent.findViewById(R.id.rl_comment);
        CircleImageView ivAvatar = (CircleImageView) parent.findViewById(R.id.iv_avatar);
        TextView tvName  = (TextView) parent.findViewById(R.id.tv_name);
        TextView tvContent = (TextView) parent.findViewById(R.id.tv_content);
        TextView tvTime = (TextView) parent.findViewById(R.id.tv_time);
        RadioButton rbAnswer = (RadioButton) parent.findViewById(R.id.rb_answer);

        return new CommentHolder(parent,context,rlComment, ivAvatar,
                tvName,tvContent,tvTime,rbAnswer);
    }

    //设置图片
    public void setAvatar(String imageUrl) {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(context).load(imageUrl).into(ivAvatar);
        }
    }

    public void setNickname(CharSequence text) {
        tvName.setText(text);
    }

    public void setTextContent(CharSequence text) {
        tvContent.setText(text);
    }

    public void setTime(CharSequence text) {
        tvTime.setText(text);
    }

    public void setCommentNum(Integer num) {
        rbAnswer.setText(num+"");
    }

}
