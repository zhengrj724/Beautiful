package com.beautiful.beautiful.mvp.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.beautiful.beautiful.R;
import com.beautiful.beautiful.mvp.Info.ILocalVideoView;
import com.beautiful.beautiful.mvp.model.VideoInfo;
import com.beautiful.beautiful.mvp.presenter.LocalVideoPresenter;
import com.beautiful.beautiful.mvp.ui.adapter.LocalVideoAdapter;
import com.beautiful.beautiful.mvp.ui.common.BaseActivity;
import com.beautiful.beautiful.utils.HandlerKey;
import com.beautiful.beautiful.utils.RefreshUtil;
import com.beautiful.beautiful.utils.ToastUtil;
import com.beautiful.beautiful.utils.VideoProvider;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LocalVideoActivity extends BaseActivity
        implements ILocalVideoView {

    @Bind(R.id.tb_video_list)
    Toolbar tbVideoList;
    @Bind(R.id.rv_video_list)
    RecyclerView rvVideoList;
    @Bind(R.id.activity_video_list)
    LinearLayout activityVideoList;

    private List<VideoInfo> videos;
    private LocalVideoAdapter mAdapter;
    private LocalVideoPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_video);
        ButterKnife.bind(this);

        initView();
        initData();
        initListener();
    }

    //初始化界面
    private void initView() {
        setSupportActionBar(tbVideoList);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //初始化数据
    private void initData() {
        mPresenter = new LocalVideoPresenter(this);
        videos = new ArrayList<>();
        mAdapter = new LocalVideoAdapter(this,videos);
        rvVideoList.setLayoutManager(new LinearLayoutManager(this));
        rvVideoList.setAdapter(mAdapter);

        VideoProvider videoProvider = new VideoProvider(this);
        videos.addAll(videoProvider.getList());
        mAdapter.notifyDataSetChanged();
    }

    //初始化监听事件
    private void initListener() {
        mAdapter.setOnItemClickListener(new LocalVideoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_local_video:
                        ToastUtil.ShortToast(LocalVideoActivity.this,"视频"+position);
                        break;
                    case R.id.btn_upload:
                        //上传视频
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void upLoad(String filePath) {
        mPresenter.uploadFile(filePath);
    }
}
