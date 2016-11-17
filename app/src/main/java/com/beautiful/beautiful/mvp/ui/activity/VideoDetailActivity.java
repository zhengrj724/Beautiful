package com.beautiful.beautiful.mvp.ui.activity;

import android.app.ProgressDialog;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.beautiful.beautiful.R;
import com.beautiful.beautiful.config.Key;
import com.beautiful.beautiful.config.State;
import com.beautiful.beautiful.mvp.model.Tb_Comment;
import com.beautiful.beautiful.mvp.model.Tb_User;
import com.beautiful.beautiful.mvp.model.Tb_Video;
import com.beautiful.beautiful.mvp.ui.adapter.CommentAdapter;
import com.beautiful.beautiful.mvp.ui.common.BaseActivity;
import com.beautiful.beautiful.utils.HandlerKey;
import com.beautiful.beautiful.utils.HidingScrollListener;
import com.beautiful.beautiful.utils.InputUtil;
import com.beautiful.beautiful.utils.RefreshUtil;
import com.beautiful.beautiful.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class VideoDetailActivity extends BaseActivity implements
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
    @Bind(R.id.et_input)
    EditText etInput;

    private Tb_User mUser;
    private Tb_Video mVideo;
    private MediaController mMediaController;
    private CommentAdapter mAdapter;
    private List mList;
    private int fabState = State.FAB_EDIT;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HandlerKey.REFRESH_SUCCESS:
                    srlVideoDetail.setRefreshing(false);
                    break;
                case HandlerKey.REFRESH_FAIL:
                    ToastUtil.ShortToast(VideoDetailActivity.this,"刷新失败");
                    srlVideoDetail.setRefreshing(false);
                    break;
                case 1:
                    if (tvPercent.getVisibility() == View.VISIBLE) {
                        tvPercent.setText(vvVideo.getBufferPercentage() + "%");
                        mHandler.sendEmptyMessageDelayed(1, 300);
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
        initListener();
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
        mAdapter = new CommentAdapter(this,mList);
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

    private void initListener() {
        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    fabComment.setImageResource(R.drawable.ic_send_white_24dp);
                    fabComment.show();
                } else {
                    fabComment.hide();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    //初始化数据
    private void initData() {
        //添加头部
        mList.add(mVideo);
        bindComments();
    }

    //绑定评论数据
    private void bindComments() {
        BmobQuery<Tb_Comment> query = new BmobQuery<>();
        query.addWhereEqualTo("parentId",mVideo.getObjectId());
        query.setLimit(10);
        query.findObjects(new FindListener<Tb_Comment>() {
            @Override
            public void done(List<Tb_Comment> list, BmobException e) {
                if (e == null) {
                    mList.clear();
                    mList.add(mVideo);
                    mList.addAll(1,list);
                    mAdapter.notifyDataSetChanged();
                    mHandler.sendEmptyMessage(HandlerKey.REFRESH_SUCCESS);
                } else {
                    mHandler.sendEmptyMessage(HandlerKey.REFRESH_FAIL);
                }
            }
        });
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
        bindComments();
    }

    @OnClick(R.id.fab_comment)
    public void onClick() {
        if (fabState == State.FAB_EDIT) {
            //编辑评论
            etInput.setVisibility(View.VISIBLE);
            etInput.setFocusable(true);
            etInput.setFocusableInTouchMode(true);
            etInput.requestFocus();
            etInput.requestFocusFromTouch();
            InputUtil.showSoftInput(this,etInput);
            fabComment.hide();
            fabState = State.FAB_SEND;
        } else {
            //发送评论
            sendComment();
            etInput.setText("");
            etInput.setVisibility(View.INVISIBLE);
            fabComment.setImageResource(R.drawable.ic_create_white_24dp);
            InputUtil.hideSoftInput(this,etInput);
            fabState = State.FAB_EDIT;
        }
    }

    //发送评论
    private void sendComment() {
        mUser = application.getUser();
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("发送评论...");
        dialog.show();
        String comment = etInput.getText().toString().trim();

        Tb_Comment tb_comment = new Tb_Comment();
        tb_comment.setAvatar(mUser.getAvatar());
        tb_comment.setNickname(mUser.getNickName());
        tb_comment.setTextContent(comment);
        tb_comment.setParentId(mVideo.getObjectId());//视频的id

        tb_comment.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    //评论添加成功
                    doRefresh();
                    ToastUtil.LongToast(VideoDetailActivity.this,"评论成功");
                } else {
                    ToastUtil.LongToast(VideoDetailActivity.this,"评论失败");
                }
                dialog.dismiss();
            }
        });
    }
}
