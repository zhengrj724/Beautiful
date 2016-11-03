package com.beautiful.beautiful.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.beautiful.beautiful.mvp.ui.fragment.ClassifyFragment;
import com.beautiful.beautiful.mvp.ui.fragment.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.R on 2016/7/10.
 */
public class FragmentController {
    private static FragmentController instance;
    private int containerId;
    private FragmentManager manager;
    private List<Fragment> fragments;

    private FragmentController(int containerId, FragmentManager manager) {
        this.containerId = containerId;
        this.manager = manager;

        initFragment();
    }

    public static FragmentController getInstance(int containerId,FragmentManager manager) {
        instance = new FragmentController(containerId,manager);
        return instance;
    }

    //初始化
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new RecommendFragment());
        fragments.add(new ClassifyFragment());

        FragmentTransaction transaction = manager.beginTransaction();
        for (Fragment fragment : fragments) {
            transaction.add(containerId,fragment);
            transaction.hide(fragment);
        }

        transaction.show(fragments.get(0));
        transaction.commit();
    }

    //显示fragment
    public void showFragment(int position) {
        Fragment fragment = fragments.get(position);
        if (fragment.isHidden()) {
            hideFragments();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.show(fragment);
            transaction.commit();
        }
    }

    //隐藏fragment
    public void hideFragments() {
        FragmentTransaction transaction = manager.beginTransaction();
        for (Fragment fragment : fragments) {
            if (fragment != null) {
                transaction.hide(fragment);
            }
        }
        transaction.commit();
    }

    //根据position获取fragment
    public Fragment getFragment(int position) {
        return fragments==null ? null : fragments.get(position);
    }
}
