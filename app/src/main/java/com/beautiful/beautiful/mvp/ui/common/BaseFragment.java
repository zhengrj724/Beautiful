package com.beautiful.beautiful.mvp.ui.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.beautiful.beautiful.utils.BaseApplication;

/**
 * Created by Mr.R on 2016/7/10.
 */
public class BaseFragment extends Fragment {
    protected BaseActivity activity;
    protected BaseApplication application;

    public BaseFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {
        activity = (BaseActivity) getActivity();
        application = (BaseApplication) activity.getApplication();
    }

    protected void intentToActivity(Context activity, Class<? extends Activity> targetActivity) {
        Intent intent = new Intent(activity,targetActivity);
        startActivity(intent);
    }

    //强制显示软键盘
    public void showSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    public void hideSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
