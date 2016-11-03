package com.beautiful.beautiful.mvp.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.beautiful.beautiful.R;
import com.beautiful.beautiful.lib.CircleImageView;
import com.beautiful.beautiful.mvp.Info.IMainView;
import com.beautiful.beautiful.mvp.presenter.MainPresenter;
import com.beautiful.beautiful.mvp.ui.adapter.MainFragmentAdapter;
import com.beautiful.beautiful.mvp.ui.common.BaseActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, IMainView{

    @Bind(R.id.tb_main)
    Toolbar tbMain;
    @Bind(R.id.tl_main)
    TabLayout tlMain;
    @Bind(R.id.vp_main)
    ViewPager vpMain;
    @Bind(R.id.nv_menu)
    NavigationView nvMenu;
    @Bind(R.id.layout_main)
    DrawerLayout layoutMain;

    private CircleImageView civAvatar;
    private TextView tvRealName;

    private MainPresenter mPresenter;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private MainFragmentAdapter mFragmentAdapter;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        ButterKnife.bind(this);

        init();
    }

    //初始化
    private void init() {
        mPresenter = new MainPresenter(this);
        mPresenter.init();
    }

    @Override
    public void initView(List<String> titles, List<Fragment> fragments) {
        mFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager(),titles,fragments);
        vpMain.setAdapter(mFragmentAdapter);
        tlMain.setupWithViewPager(vpMain);
        initView();
    }

    private void initView() {
        setSupportActionBar(tbMain);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, layoutMain,
                R.string.draw_open, R.string.draw_close);
        layoutMain.addDrawerListener(mActionBarDrawerToggle);

        initHeaderView();
        nvMenu.setNavigationItemSelectedListener(this);
    }

    //初始化侧滑栏头部
    private void initHeaderView() {
        View view = nvMenu.inflateHeaderView(R.layout.layout_menu_head);
        civAvatar = (CircleImageView) view.findViewById(R.id.iv_user_head);
        tvRealName = (TextView) view.findViewById(R.id.tv_user_name);

        civAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"点击了头像",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //初始化工具栏
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_tool,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //工具栏按钮点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.menu_notification:
                Toast.makeText(this,"通知",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_search:
                Toast.makeText(this,"搜索",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //同步菜单按钮样式
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //设置菜单按钮样式改变
        mActionBarDrawerToggle.syncState();
    }

    //侧滑栏菜单点击事件
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_hot:
                //热门
                Snackbar.make(layoutMain,"热门",Snackbar.LENGTH_SHORT).show();
                break;

            case R.id.menu_feedback:
                //反馈意见
                Snackbar.make(layoutMain,"反馈意见",Snackbar.LENGTH_SHORT).show();
                break;

            case R.id.menu_provide:
                //提供素材
                Snackbar.make(layoutMain,"提供素材",Snackbar.LENGTH_SHORT).show();
                break;

            case R.id.menu_set:
                //设置
                Snackbar.make(layoutMain,"设置",Snackbar.LENGTH_SHORT).show();
                break;

            default:
                break;
        }

        item.setChecked(true);
        return true;
    }

    //按两次返回键退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Snackbar.make(layoutMain,"再按一次退出",Snackbar.LENGTH_SHORT).show();
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
