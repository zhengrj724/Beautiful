package com.beautiful.beautiful.mvp.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.beautiful.beautiful.R;
import com.beautiful.beautiful.mvp.model.VideoInfo;
import com.beautiful.beautiful.mvp.ui.adapter.LocalVideoAdapter;
import com.beautiful.beautiful.mvp.ui.common.BaseActivity;
import com.beautiful.beautiful.utils.RefreshUtil;
import com.beautiful.beautiful.utils.VideoProvider;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VideoListActivity extends BaseActivity {

    @Bind(R.id.tb_video_list)
    Toolbar tbVideoList;
    @Bind(R.id.rv_video_list)
    RecyclerView rvVideoList;
    @Bind(R.id.srl_video_list)
    SwipeRefreshLayout srlVideoList;
    @Bind(R.id.activity_video_list)
    LinearLayout activityVideoList;

    private List<VideoInfo> videos;
    private LocalVideoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        ButterKnife.bind(this);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        setSupportActionBar(tbVideoList);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RefreshUtil.initRefreshView(srlVideoList);
    }

    private void initData() {
        videos = new ArrayList<>();
        mAdapter = new LocalVideoAdapter(this,videos);
        rvVideoList.setLayoutManager(new LinearLayoutManager(this));
        rvVideoList.setAdapter(mAdapter);

        VideoProvider videoProvider = new VideoProvider(this);
        videos.addAll(videoProvider.getList());
        mAdapter.notifyDataSetChanged();
    }

    private void initListener() {
        srlVideoList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

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
}
