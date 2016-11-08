package com.beautiful.beautiful.mvp.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.beautiful.beautiful.R;
import com.beautiful.beautiful.mvp.model.Tb_Video;
import com.beautiful.beautiful.mvp.model.VideoInfo;
import com.beautiful.beautiful.mvp.ui.holder.LocalVideoHolder;
import com.beautiful.beautiful.mvp.ui.holder.VideoHolder;

import java.util.List;

/**
 * Created by Mr.R on 2016/7/12.
 */
public class LocalVideoAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<VideoInfo> videos;
    private int loading = -1;

    public LocalVideoAdapter(Context context, List<VideoInfo> videos) {
        this.context = context;
        this.videos = videos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType != loading) {
            //不在加载
            final View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_local_video, parent, false);
            return LocalVideoHolder.newInstance(view);
        } else {
            //在加载
            final View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder instanceof LocalVideoHolder) {
            final LocalVideoHolder holder = (LocalVideoHolder) viewHolder;
            final VideoInfo video = videos.get(position);

            holder.setImageView(context,video.getPath());
            holder.setTvTitle(video.getTitle());
            holder.setTvDuration(video.getDuration()+"");

            if (listener != null) {
                holder.llLocalVideo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = holder.getLayoutPosition();
                        listener.onItemClick(holder.llLocalVideo,position);
                    }
                });
            }
        } else {
//            ((LoadingViewHolder)viewHolder).progressBar.s;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(videos.get(position) != null){
            return 1;
        }else{
            return loading;
        }
    }

    @Override
    public int getItemCount() {
        return videos == null ? 0 : videos.size();
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
    private class LoadingViewHolder extends RecyclerView.ViewHolder{
        public ProgressBar progressBar;
        public TextView textView;

        public LoadingViewHolder(View view){
            super(view);
            progressBar= (ProgressBar) view.findViewById(R.id.pb_loading);
            textView=(TextView)view.findViewById(R.id.tv_loading);
        }
    }

}
