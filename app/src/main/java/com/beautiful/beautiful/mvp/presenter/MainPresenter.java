package com.beautiful.beautiful.mvp.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.beautiful.beautiful.mvp.Info.IMainView;
import com.beautiful.beautiful.mvp.ui.fragment.ClassifyFragment;
import com.beautiful.beautiful.mvp.ui.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.R on 2016/10/1.
 */
public class MainPresenter {

    private IMainView view;

    public MainPresenter(IMainView view) {
        this.view = view;
    }

    public void init() {
        initView();
    }

    public void initView() {
        List<String> titles = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();

        titles.add("所有");
        titles.add("分类");

        fragments.add(new VideoFragment());
        fragments.add(new ClassifyFragment());

        view.initView(titles,fragments);
    }

}
