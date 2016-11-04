package com.beautiful.beautiful.mvp.ui.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.beautiful.beautiful.R;
import com.beautiful.beautiful.config.Key;
import com.beautiful.beautiful.mvp.model.Tb_Comment;
import com.beautiful.beautiful.mvp.model.Tb_Video;
import com.beautiful.beautiful.mvp.ui.adapter.CommentAdapter;
import com.beautiful.beautiful.mvp.ui.common.BaseActivity;
import com.beautiful.beautiful.utils.HandlerKey;
import com.beautiful.beautiful.utils.HidingScrollListener;
import com.beautiful.beautiful.utils.RefreshUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoDetail extends BaseActivity implements
        SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.tb_video)
    Toolbar tbVideo;
    @Bind(R.id.vv_video)
    VideoView vvVideo;
    @Bind(R.id.fl_background)
    FrameLayout flBackground;
    @Bind(R.id.rv_video_detail)
    RecyclerView rvVideoDetail;
    @Bind(R.id.srl_video_detail)
    SwipeRefreshLayout srlVideoDetail;
    @Bind(R.id.fab_comment)
    FloatingActionButton fabComment;
    @Bind(R.id.pb_loading)
    ProgressBar pbLoading;
    @Bind(R.id.tv_percent)
    TextView tvPercent;

    private Tb_Video mVideo;
    private MediaController mMediaController;
    private CommentAdapter mAdapter;
    private List mList;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HandlerKey.REFRESH_SUCCESS:
                    srlVideoDetail.setRefreshing(false);
                    break;
                case HandlerKey.REFRESH_FAIL:
                    srlVideoDetail.setRefreshing(false);
                    break;
                case 1:
                    if (tvPercent.getVisibility() == View.VISIBLE) {

                        tvPercent.setText(vvVideo.getBufferPercentage()+"%");
                        mHandler.sendEmptyMessageDelayed(1,300);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        ButterKnife.bind(this);
        initView();
        init();
    }

    private void initView() {
        setSupportActionBar(tbVideo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RefreshUtil.initRefreshView(srlVideoDetail, this);
    }

    private void init() {
        Intent intent = getIntent();
        mVideo = (Tb_Video) intent.getSerializableExtra(Key.VIDEO);

        if (mVideo != null) {
            //获取视频地址
            Uri videoUri = Uri.parse(mVideo.getVideo());
            vvVideo.setVideoURI(videoUri);
            mHandler.sendEmptyMessage(1);

            //将播放控件与媒体控制器关联
            mMediaController = new MediaController(this);
            vvVideo.setMediaController(mMediaController);
            mMediaController.setMediaPlayer(vvVideo);
            mMediaController.setAnchorView(flBackground);
        }

        //视频准备完毕
        vvVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                //开始播放
                pbLoading.setVisibility(View.GONE);
                tvPercent.setVisibility(View.GONE);
                vvVideo.start();
                vvVideo.requestFocus();
            }
        });

        //播放错误
        vvVideo.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                Snackbar.make(flBackground, "资源不存在", Snackbar.LENGTH_SHORT).show();
                return true;
            }
        });

        //播放完毕
        vvVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                vvVideo.seekTo(0);
                mMediaController.show();
            }
        });

        initRecyclerView();
    }

    //暂停
    @Override
    protected void onPause() {
        super.onPause();
        if (vvVideo.isPlaying()) {
            vvVideo.pause();
        }
    }

    //初始化列表
    private void initRecyclerView() {
        mList = new ArrayList();
        mAdapter = new CommentAdapter(mList);
        rvVideoDetail.setLayoutManager(new LinearLayoutManager(this));
        rvVideoDetail.setAdapter(mAdapter);

        //评论item点击事件
        mAdapter.setOnItemClickListener(new CommentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Snackbar.make(flBackground, position + "", Snackbar.LENGTH_SHORT).show();
            }
        });

        //列表滚动监听事件
        rvVideoDetail.addOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                fabComment.hide();
            }

            @Override
            public void onShow() {
                fabComment.show();
            }
        });

        initData();
    }

    //初始化数据
    private void initData() {
        //添加头部
        mList.add(mVideo);

        for (int i = 0; i < 20; i++) {
            mList.add(new Tb_Comment());
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.video_tool, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //后退
                finish();
                break;
            case R.id.menu_collect:
                //收藏
                break;
            case R.id.menu_share:
                //分享
                break;
            case R.id.menu_replay:
                //重新播放
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //下拉刷新
    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    doRefresh();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mHandler.sendEmptyMessageDelayed(HandlerKey.REFRESH_SUCCESS, 600);
            }
        }).start();
    }

    private void doRefresh() {

    }

    @OnClick(R.id.fab_comment)
    public void onClick() {
        Snackbar.make(flBackground, "评论功能暂未开放", Snackbar.LENGTH_SHORT).show();
    }
}
