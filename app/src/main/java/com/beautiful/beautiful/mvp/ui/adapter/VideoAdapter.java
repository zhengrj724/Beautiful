package com.beautiful.beautiful.mvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.beautiful.beautiful.R;
import com.beautiful.beautiful.mvp.model.Tb_Video;
import com.beautiful.beautiful.mvp.ui.holder.VideoHolder;

import java.util.List;

/**
 * Created by Mr.R on 2016/7/12.
 */
public class VideoAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Fragment fragment;
    private List<Tb_Video> videos;
    private int loading = -1;

    public VideoAdapter(Fragment fragment, List<Tb_Video> videos) {
        this.fragment = fragment;
        this.videos = videos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType != loading) {
            //不在加载
            final View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_video, parent, false);
            return VideoHolder.newInstance(fragment,view);
        } else {
            //在加载
            final View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder instanceof VideoHolder) {
            final VideoHolder holder = (VideoHolder) viewHolder;
            final Tb_Video video = videos.get(position);

            holder.setImageView(video.getImage());
            holder.setTvTheme(video.getTheme());
            holder.setCbBrowse(video.getBrowseNum()+"");
            holder.setCbComment(video.getCommentNum()+"");
            holder.setCbDuration(video.getDuration());

            if (listener != null) {
                holder.cvVideo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = holder.getLayoutPosition();
                        listener.onItemClick(holder.cvVideo,position);
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
            return super.getItemViewType(position);
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
