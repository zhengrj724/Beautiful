package com.beautiful.beautiful.mvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Mr.R on 2016/9/24.
 */
public class MainFragmentAdapter extends FragmentStatePagerAdapter{
    List<String> mTitle;
    List<Fragment> mFragment;

    public MainFragmentAdapter(FragmentManager Manager,
                               List<String> titles,
                               List<Fragment> fragments) {
        super(Manager);
        mTitle = titles;
        mFragment = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }
}
