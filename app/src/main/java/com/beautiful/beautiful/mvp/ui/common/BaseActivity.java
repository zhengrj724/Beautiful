package com.beautiful.beautiful.mvp.ui.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.beautiful.beautiful.config.Config;

/**
 * Created by Mr.R on 2016/7/10.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected String tag;
    protected static BaseApplication application;
    protected SharedPreferences preferences;
    protected final String URL = Config.URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {
        application = (BaseApplication) getApplication();
    }

    protected void intentToActivity(Class<? extends Activity> targetActivity) {
        Intent intent = new Intent(this,targetActivity);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
