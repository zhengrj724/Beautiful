package com.beautiful.beautiful.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.beautiful.beautiful.R;
import com.beautiful.beautiful.mvp.model.entity.Tb_Comment;
import com.beautiful.beautiful.mvp.model.entity.Tb_Video;
import com.beautiful.beautiful.mvp.ui.holder.CommentHolder;

import java.util.List;

/**
 * Created by Mr.R on 2016/7/12.
 */
public class CommentAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List data;
    private static final int LOADING = -1;
    private static final int HEAD = 0;
    private static final int COMMENT = 1;

    public CommentAdapter(List data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case LOADING:
                //加载
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_loading, parent, false);
                return new LoadingHolder(view);
            case HEAD:
                //头部
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_video_head, parent, false);
                return new HeadHolder(view);
            case COMMENT:
                //评论
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_comment, parent, false);
                return CommentHolder.newInstance(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder instanceof CommentHolder) {
            final CommentHolder holder = (CommentHolder) viewHolder;
            Tb_Comment tbComment = (Tb_Comment) data.get(position);

            if (listener != null) {
                holder.rlComment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = holder.getLayoutPosition();
                        listener.onItemClick(holder.rlComment,position);
                    }
                });
            }
        } else if (viewHolder instanceof HeadHolder) {

            HeadHolder holder = (HeadHolder) viewHolder;
            Tb_Video tbVideo = (Tb_Video) data.get(position);

            holder.tvTitle.setText(tbVideo.getTheme());
            holder.tvPlayNum.setText(tbVideo.getBrowseNum()+"");
            holder.tvCommentNum.setText(tbVideo.getCommentNum()+"");
            holder.tvDurationTime.setText(tbVideo.getDuration());
            holder.tvDescribe.setText(tbVideo.getDescribe());

        } else {
//            ((LoadingViewHolder)viewHolder).progressBar.s;
        }
    }

    @Override
    public int getItemViewType(int position) {
        Object object = data.get(position);

        if(object != null){
            if (object instanceof Tb_Comment) {
                return COMMENT;
            } else {
                return HEAD;
            }
        }else{
            return LOADING;
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    //设置点击事件接口，回调等
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    //上拉加载的viewHolder
    private class LoadingHolder extends RecyclerView.ViewHolder{
        public ProgressBar progressBar;
        public TextView textView;

        public LoadingHolder(View view){
            super(view);
            progressBar= (ProgressBar) view.findViewById(R.id.pb_loading);
            textView=(TextView)view.findViewById(R.id.tv_loading);
        }
    }

    //头部的ViewHolder
    private class HeadHolder extends RecyclerView.ViewHolder{
        public TextView tvTitle;
        public TextView tvPlayNum;
        public TextView tvCommentNum;
        public TextView tvDurationTime;
        public TextView tvDescribe;

        public HeadHolder(View view){
            super(view);
            tvTitle= (TextView) view.findViewById(R.id.tv_title);
            tvPlayNum=(TextView)view.findViewById(R.id.tv_playNum);
            tvCommentNum=(TextView)view.findViewById(R.id.tv_commentNum);
            tvDurationTime=(TextView)view.findViewById(R.id.tv_durationTime);
            tvDescribe=(TextView)view.findViewById(R.id.tv_describe);
        }
    }

}
