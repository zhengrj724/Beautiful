package com.beautiful.beautiful.mvp.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.beautiful.beautiful.R;
import com.beautiful.beautiful.mvp.Info.ILoginView;
import com.beautiful.beautiful.mvp.model.Tb_User;
import com.beautiful.beautiful.mvp.ui.activity.LoginActivity;
import com.beautiful.beautiful.utils.ToastUtil;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by krkj on 2016/11/7.
 */

public class LoginPresenter {
    private ILoginView view;
    private ProgressDialog mProgressDialog;

    public LoginPresenter(ILoginView view) {
        this.view = view;
    }

    //登录
    public void login() {
        showProgressDialog("登录中，请稍候...");
        String username = ((LoginActivity)view).etUsername.getText().toString().trim();
        String password = ((LoginActivity)view).etPassword.getText().toString().trim();

        doLogin(username,password);
    }

    //注册
    public void register() {
        View registerDialog = LayoutInflater.from((LoginActivity)view).inflate(R.layout.dialog_register,null);
        final AlertDialog dialog = new AlertDialog
                .Builder((LoginActivity)view)
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
        mProgressDialog = new ProgressDialog((LoginActivity)view);
        mProgressDialog.setMessage(text);
        mProgressDialog.show();
    }

    //登录账号
    private void doLogin(String username,String password) {
        if (!username.isEmpty() && !password.isEmpty()) {
            BmobUser bu = new BmobUser();
            bu.setUsername(username);
            bu.setPassword(password);
            bu.login(new SaveListener<BmobUser>() {

                @Override
                public void done(BmobUser bmobUser, BmobException e) {
                    if(e==null){
                        ToastUtil.ShortToast((LoginActivity)view,"登录成功");
                        //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
                        //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
                    }else{
                        ToastUtil.ShortToast((LoginActivity)view,"登录失败"+e.getMessage());
                    }
                    mProgressDialog.dismiss();
                }
            });
        } else {
            mProgressDialog.dismiss();
            Snackbar.make(((LoginActivity)view).activityLogin,"用户名或密码不能为空",Snackbar.LENGTH_SHORT).show();
        }
    }

    //用户注册账号到Bmob
    private void doRegister(String username, String password) {
        if (!username.isEmpty() && !password.isEmpty()) {
            Tb_User user = new Tb_User(username,password);
            user.signUp(new SaveListener<Tb_User>() {
                @Override
                public void done(Tb_User s, BmobException e) {
                    if(e == null){
                        ToastUtil.ShortToast((LoginActivity)view,s.toString());
                    }else{
                        ToastUtil.ShortToast((LoginActivity)view,e.getMessage());
                    }
                    mProgressDialog.dismiss();
                }
            });
        } else {
            mProgressDialog.dismiss();
            Snackbar.make(((LoginActivity)view).activityLogin,"用户名或密码不能为空",Snackbar.LENGTH_SHORT).show();
        }
    }

    public void forgetPassword() {

    }
}
