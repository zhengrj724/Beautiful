package com.beautiful.beautiful.mvp.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beautiful.beautiful.R;
import com.beautiful.beautiful.mvp.Info.ILoginView;
import com.beautiful.beautiful.mvp.presenter.LoginPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    @Bind(R.id.et_username)
    public EditText etUsername;
    @Bind(R.id.til_username)
    TextInputLayout tilUsername;
    @Bind(R.id.et_password)
    public EditText etPassword;
    @Bind(R.id.til_password)
    TextInputLayout tilPassword;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @Bind(R.id.tv_register)
    TextView tvRegister;
    @Bind(R.id.activity_login)
    public RelativeLayout activityLogin;

    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        mLoginPresenter = new LoginPresenter(this);
    }

    @OnClick({R.id.btn_login, R.id.tv_forget_password, R.id.tv_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_forget_password:
                forgetPassword();
                break;
            case R.id.tv_register:
                register();
                break;
        }
    }

    @Override
    public void login() {
        mLoginPresenter.login();
    }

    @Override
    public void register() {
        mLoginPresenter.register();
    }

    @Override
    public void forgetPassword() {
        mLoginPresenter.forgetPassword();
    }
}
