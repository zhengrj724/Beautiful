package com.beautiful.beautiful.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beautiful.beautiful.R;
import com.beautiful.beautiful.config.Key;
import com.beautiful.beautiful.mvp.Info.IVideoView;
import com.beautiful.beautiful.mvp.model.Tb_Video;
import com.beautiful.beautiful.mvp.presenter.VideoPresenter;
import com.beautiful.beautiful.mvp.ui.activity.VideoDetail;
import com.beautiful.beautiful.mvp.ui.adapter.VideoAdapter;
import com.beautiful.beautiful.mvp.ui.common.BaseFragment;
import com.beautiful.beautiful.utils.HandlerKey;
import com.beautiful.beautiful.utils.RefreshUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mr.R on 2016/9/24.
 */
public class RecommendFragment extends BaseFragment implements
        SwipeRefreshLayout.OnRefreshListener, IVideoView {

    @Bind(R.id.rv_video)
    RecyclerView rvVideo;
    @Bind(R.id.srl_video)
    SwipeRefreshLayout srlVideo;

    private View mView;
    private List<Tb_Video> mList;
    private VideoAdapter mAdapter;
    private VideoPresenter mPresenter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HandlerKey.REFRESH_SUCCESS:
                    srlVideo.setRefreshing(false);
                    mAdapter.notifyDataSetChanged();
                    break;
                case HandlerKey.REFRESH_FAIL:
                    srlVideo.setRefreshing(false);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_recommed, container, false);
        ButterKnife.bind(this, mView);
        init();
        return mView;
    }

    //初始化
    private void init() {
        mPresenter = new VideoPresenter(this);

        rvVideo.setLayoutManager(new LinearLayoutManager(activity));
        mList = new ArrayList<>();
        mAdapter = new VideoAdapter(this,mList);
        rvVideo.setAdapter(mAdapter);

        RefreshUtil.initRefreshView(srlVideo,this);
        initEvent();
        doRefresh();
    }

    //初始化监听事件
    private void initEvent() {
        mAdapter.setOnItemClickListener(new VideoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //跳转到视频详情
                Intent intent = new Intent(activity,VideoDetail.class);
                intent.putExtra(Key.VIDEO, mList.get(position));
                startActivity(intent);
            }
        });
    }

    //下拉刷新事件
    @Override
    public void onRefresh() {
        mPresenter.doRefresh();
    }

    //刷新数据
    @Override
    public void doRefresh() {
        if (!srlVideo.isRefreshing()) {
            srlVideo.setRefreshing(true);
        }
        mList.clear();
        mPresenter.refreshVideo(mList,mHandler);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
