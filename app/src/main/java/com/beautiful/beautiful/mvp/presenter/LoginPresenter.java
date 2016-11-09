package com.beautiful.beautiful.mvp.presenter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.beautiful.beautiful.R;
import com.beautiful.beautiful.mvp.Info.ILoginView;
import com.beautiful.beautiful.mvp.model.Tb_User;
import com.beautiful.beautiful.mvp.ui.activity.LoginActivity;
import com.beautiful.beautiful.mvp.ui.activity.MainActivity;
import com.beautiful.beautiful.mvp.ui.common.BaseApplication;
import com.beautiful.beautiful.utils.ToastUtil;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by krkj on 2016/11/7.
 */

public class LoginPresenter {
    private LoginActivity activity;
    private ProgressDialog mProgressDialog;

    public LoginPresenter(ILoginView view) {
        activity = (LoginActivity) view;
    }

    //登录
    public void login() {
        showProgressDialog("登录中，请稍候...");
        String username = activity.etUsername.getText().toString().trim();
        String password = activity.etPassword.getText().toString().trim();

        doLogin(username,password);
    }

    //注册
    public void register() {
        View registerDialog = LayoutInflater.from(activity).inflate(R.layout.dialog_register,null);
        final AlertDialog dialog = new AlertDialog
                .Builder(activity)
                .setView(registerDialog)
                .create();

        final EditText etUsername = (EditText) registerDialog.findViewById(R.id.et_username);
        final EditText etPassword = (EditText) registerDialog.findViewById(R.id.et_password);
        Button btnRegister = (Button) registerDialog.findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                showProgressDialog("注册中，请稍候...");
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                doRegister(username,password);
            }
        });

        dialog.show();
    }

    //显示加载
    private void showProgressDialog(String text) {
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage(text);
        mProgressDialog.show();
    }

    //登录账号
    private void doLogin(String username,String password) {
        if (!username.isEmpty() && !password.isEmpty()) {
            BmobUser bu = new BmobUser();
            bu.setUsername(username);
            bu.setPassword(password);
            bu.login(new SaveListener<Tb_User>() {

                @Override
                public void done(Tb_User user, BmobException e) {
                    mProgressDialog.dismiss();
                    if(e==null){
                        BaseApplication application = (BaseApplication) activity.getApplication();
                        application.setUser(user);
                        Intent intent = new Intent(activity,MainActivity.class);
                        activity.startActivity(intent);
                        activity.finish();
                    }else{
                        ToastUtil.ShortToast(activity,"登录失败"+e.getMessage());
                    }
                }
            });
        } else {
            mProgressDialog.dismiss();
            Snackbar.make(activity.activityLogin,"用户名或密码不能为空",Snackbar.LENGTH_SHORT).show();
        }
    }

    //用户注册账号到Bmob
    private void doRegister(String username, final String password) {
        if (!username.isEmpty() && !password.isEmpty()) {
            Tb_User user = new Tb_User(username,password);
            user.signUp(new SaveListener<Tb_User>() {
                @Override
                public void done(Tb_User s, BmobException e) {
                    if(e == null){
                        //注册成功，自动登录
                        s.setPassword(password);
                        s.login(new SaveListener<Tb_User>() {
                            @Override
                            public void done(Tb_User user, BmobException e) {
                                if (e == null) {
                                    //登录成功，跳转到主界面
                                    mProgressDialog.dismiss();
                                    BaseApplication application = (BaseApplication) activity.getApplication();
                                    application.setUser(user);
                                    Intent intent = new Intent(activity, MainActivity.class);
                                    activity.startActivity(intent);
                                    activity.finish();
                                } else {
                                    mProgressDialog.dismiss();
                                    ToastUtil.ShortToast(activity,"登录失败"+e.getMessage());
                                }
                            }
                        });
                    }else{
                        mProgressDialog.dismiss();
                        ToastUtil.ShortToast(activity,"注册失败"+e.getMessage());
                    }
                }
            });
        } else {
            mProgressDialog.dismiss();
            Snackbar.make(activity.activityLogin,"用户名或密码不能为空",Snackbar.LENGTH_SHORT).show();
        }
    }

    public void forgetPassword() {

    }
}
