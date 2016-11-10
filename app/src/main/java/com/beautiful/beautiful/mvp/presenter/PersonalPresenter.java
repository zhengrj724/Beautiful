package com.beautiful.beautiful.mvp.presenter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.beautiful.beautiful.R;
import com.beautiful.beautiful.lib.CircleImageView;
import com.beautiful.beautiful.mvp.Info.IPersonalView;
import com.beautiful.beautiful.mvp.model.Tb_User;
import com.beautiful.beautiful.mvp.ui.activity.LoginActivity;
import com.beautiful.beautiful.mvp.ui.common.BaseApplication;
import com.beautiful.beautiful.mvp.ui.fragment.PersonalFragment;
import com.beautiful.beautiful.utils.ToastUtil;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by krkj on 2016/11/10.
 */

public class PersonalPresenter {
    private IPersonalView view;
    private Activity activity;
    private AlertDialog dialog;
    private BaseApplication application;

    public PersonalPresenter(IPersonalView view) {
        this.view = view;
        this.activity = ((PersonalFragment)view).getActivity();
        this.application = (BaseApplication) ((PersonalFragment)view).getActivity().getApplication();
    }

    //点击头像事件
    public void avatarAction(CircleImageView civAvatar, final TextView tvNickname) {
        //先判断用户是否登录
        if (application.getUser() == null) {
            //未登录
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
            activity.finish();
        } else {
            //跳出dialog
            final View dialogView = LayoutInflater.from(activity)
                    .inflate(R.layout.dialog_personal_set,null);
            TextView tvAvatarSet = (TextView) dialogView.findViewById(R.id.tv_avatar_set);
            TextView tvNickNameSet = (TextView) dialogView.findViewById(R.id.tv_nickname_set);

            tvAvatarSet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtil.ShortToast(activity,"设置头像");
                    dialog.dismiss();
                }
            });

            tvNickNameSet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    showSetNickname(tvNickname);
                }
            });

            dialog = new AlertDialog.Builder(activity)
                    .setView(dialogView).create();
            dialog.show();
        }
    }

    //我的收藏
    public void myCollect() {

    }

    //我的上传
    public void myUpload() {

    }

    //我看过的
    public void myBrowsed() {

    }

    //设置
    public void settings() {

    }

    //退出登录
    public void logout() {
        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setTitle("提醒")
                .setMessage("确定退出登录吗？")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        application.setUser(null);
                        Intent intent = new Intent(activity,LoginActivity.class);
                        activity.startActivity(intent);
                        activity.finish();
                    }
                })
                .create();
        dialog.show();
    }

    //显示修改昵称的对话框
    private void showSetNickname(final TextView tvNickname) {
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_nickname,null);
        final EditText etNickname = (EditText) view.findViewById(R.id.et_nickname);

        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setView(view)
                .setNegativeButton("不是现在",null)
                .setPositiveButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setNickname(tvNickname,etNickname.getText().toString().trim());
                    }
                })
                .create();
        dialog.show();
    }

    //修改昵称
    private void setNickname(final TextView tvNickname, final String newName) {
        final ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("修改中...");
        progressDialog.show();

        Tb_User newUser = new Tb_User();
        newUser.setNickName(newName);
        Tb_User user = Tb_User.getCurrentUser(Tb_User.class);
        newUser.update(user.getObjectId(),new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    tvNickname.setText(newName);
                    ToastUtil.ShortToast(activity,"修改昵称成功");
                }else{
                    ToastUtil.ShortToast(activity,"修改失败"+e.getMessage());
                }
                progressDialog.dismiss();
            }
        });
    }
}
