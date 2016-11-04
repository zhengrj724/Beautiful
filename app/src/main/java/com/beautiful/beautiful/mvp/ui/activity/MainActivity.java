package com.beautiful.beautiful.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.beautiful.beautiful.R;
import com.beautiful.beautiful.mvp.ui.common.BaseActivity;
import com.beautiful.beautiful.utils.FragmentController;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.bnv_main_guide)
    BottomNavigationView bnvMainGuide;
    @Bind(R.id.fl_temp_layout)
    FrameLayout flTempLayout;

    private FragmentController mFragmentController;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
        initListener();
    }

    //初始化界面
    private void initView() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        mFragmentController = FragmentController.getInstance(R.id.fl_temp_layout,fragmentManager);
    }

    //初始化监听事件
    private void initListener() {
        bnvMainGuide.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_recommend:
                        mFragmentController.showFragment(0);
                        break;
                    case R.id.menu_classify:
                        mFragmentController.showFragment(1);
                        break;
                    case R.id.menu_personal:
                        mFragmentController.showFragment(2);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    //按两次返回键退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Snackbar.make(flTempLayout, "再按一次退出", Snackbar.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
