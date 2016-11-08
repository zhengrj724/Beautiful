package com.beautiful.beautiful.mvp.ui.fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beautiful.beautiful.R;
import com.beautiful.beautiful.lib.CircleImageView;
import com.beautiful.beautiful.mvp.ui.activity.LoginActivity;
import com.beautiful.beautiful.mvp.ui.activity.VideoListActivity;
import com.beautiful.beautiful.mvp.ui.common.BaseFragment;
import com.beautiful.beautiful.utils.ToastUtil;

import java.io.File;
import java.io.FileNotFoundException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UploadFileListener;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Mr.R on 2016/9/24.
 */
public class PersonalFragment extends BaseFragment {

    @Bind(R.id.tb_personal)
    Toolbar tbPersonal;
    @Bind(R.id.civ_avatar)
    CircleImageView civAvatar;
    @Bind(R.id.tv_collect_num)
    TextView tvCollectNum;
    @Bind(R.id.rl_my_collect)
    RelativeLayout rlMyCollect;
    @Bind(R.id.rl_my_upload)
    RelativeLayout rlMyUpload;
    @Bind(R.id.rl_browsed)
    RelativeLayout rlBrowsed;
    @Bind(R.id.rl_settings)
    RelativeLayout rlSettings;
    @Bind(R.id.fab_upload)
    FloatingActionButton fabUpload;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.civ_avatar, R.id.rl_my_collect, R.id.rl_my_upload,
            R.id.rl_browsed, R.id.rl_settings, R.id.fab_upload})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.civ_avatar:
                intentToActivity(activity, LoginActivity.class);
                break;
            case R.id.rl_my_collect:
                ToastUtil.ShortToast(activity,"我的收藏");
                break;
            case R.id.rl_my_upload:
                ToastUtil.ShortToast(activity,"我的上传");
                break;
            case R.id.rl_browsed:
                ToastUtil.ShortToast(activity,"我看过的");
                break;
            case R.id.rl_settings:
                ToastUtil.ShortToast(activity,"设置");
                break;
            case R.id.fab_upload:
                intentToActivity(activity, VideoListActivity.class);
                break;
        }
    }

    public void uploadFile(String path) {
        BmobFile bmobFile = new BmobFile(new File(path));
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                ToastUtil.ShortToast(activity,"完成");
            }

            @Override
            public void onProgress(Integer value) {
                ToastUtil.ShortToast(activity,"进度"+value+"%");
            }
        });
    }
}
