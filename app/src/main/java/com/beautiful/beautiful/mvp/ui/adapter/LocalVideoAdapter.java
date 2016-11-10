package com.beautiful.beautiful.mvp.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.beautiful.beautiful.R;
import com.beautiful.beautiful.config.State;
import com.beautiful.beautiful.mvp.model.Tb_Video;
import com.beautiful.beautiful.mvp.model.VideoInfo;
import com.beautiful.beautiful.mvp.ui.activity.LocalVideoActivity;
import com.beautiful.beautiful.mvp.ui.holder.LocalVideoHolder;
import com.beautiful.beautiful.mvp.ui.holder.VideoHolder;
import com.beautiful.beautiful.utils.ToastUtil;

import java.io.File;
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by Mr.R on 2016/7/12.
 */
public class LocalVideoAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<VideoInfo> videos;

    public LocalVideoAdapter(Context context, List<VideoInfo> videos) {
        this.context = context;
        this.videos = videos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //不在加载
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_local_video, parent, false);
        return LocalVideoHolder.newInstance(view,context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder instanceof LocalVideoHolder) {
            final LocalVideoHolder holder = (LocalVideoHolder) viewHolder;
            final VideoInfo video = videos.get(position);

            holder.setImageView(context, video.getPath());
            holder.setTvTitle(video.getTitle());
            holder.setTvDuration(video.getDuration());
            holder.setTvSize(video.getSize());

            if (listener != null) {
                holder.llLocalVideo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = holder.getLayoutPosition();
                        listener.onItemClick(holder.llLocalVideo, position);
                    }
                });

                holder.btnUpload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = holder.getLayoutPosition();
                        listener.onItemClick(holder.btnUpload, position);
                        View dialogView = LayoutInflater.from(context)
                                .inflate(R.layout.dialog_video_type,null);
                        AlertDialog dialog = new AlertDialog.Builder(context)
                                .setView(dialogView)
                                .setNegativeButton("取消", null)
                                .setPositiveButton("上传", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if (holder.getButtonState() == State.UPLOADING) {
                                            holder.cancel();
                                        } else {
                                            holder.upload(video.getPath());
                                        }
                                    }
                                })
                                .create();
                        dialog.show();
                    }
                });
            }
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

}
