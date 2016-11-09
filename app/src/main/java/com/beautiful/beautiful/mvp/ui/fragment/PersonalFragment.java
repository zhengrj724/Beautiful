package com.beautiful.beautiful.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beautiful.beautiful.R;
import com.beautiful.beautiful.lib.CircleImageView;
import com.beautiful.beautiful.mvp.model.Tb_User;
import com.beautiful.beautiful.mvp.ui.activity.LocalVideoActivity;
import com.beautiful.beautiful.mvp.ui.activity.LoginActivity;
import com.beautiful.beautiful.mvp.ui.common.BaseFragment;
import com.beautiful.beautiful.utils.ToastUtil;
import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @Bind(R.id.tv_nickname)
    TextView tvNickname;
    @Bind(R.id.tv_my_upload_num)
    TextView tvMyUploadNum;
    @Bind(R.id.tv_my_browsed_num)
    TextView tvMyBrowsedNum;
    @Bind(R.id.rl_logout)
    RelativeLayout rlLogout;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        Tb_User userInfo = application.getUser();

        if (userInfo != null) {
            String avatarUrl = userInfo.getAvatar();
            if (avatarUrl != null) {
                Glide.with(activity).load(avatarUrl).into(civAvatar);
            }
            tvNickname.setText(userInfo.getNickName());
            tvCollectNum.setText(userInfo.getCollectNum() + "");
            tvMyUploadNum.setText(userInfo.getMyUploadNum() + "");
            tvMyBrowsedNum.setText(userInfo.getMyBrowsedNum() + "");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.civ_avatar, R.id.rl_my_collect, R.id.rl_my_upload,
            R.id.rl_browsed, R.id.rl_settings, R.id.fab_upload, R.id.rl_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.civ_avatar:
                Intent intent = new Intent(activity, LoginActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.rl_my_collect:
                ToastUtil.ShortToast(activity, "我的收藏");
                break;
            case R.id.rl_my_upload:
                ToastUtil.ShortToast(activity, "我的上传");
                break;
            case R.id.rl_browsed:
                ToastUtil.ShortToast(activity, "我看过的");
                break;
            case R.id.rl_settings:
                ToastUtil.ShortToast(activity, "设置");
                break;
            case R.id.fab_upload:
                intentToActivity(activity, LocalVideoActivity.class);
                break;
            case R.id.rl_logout:
                application.setUser(null);
                intentToActivity(activity,LoginActivity.class);
                activity.finish();
                break;
        }
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 0 && resultCode == activity.RESULT_OK) {
//            initData();
//        }
//    }
}
