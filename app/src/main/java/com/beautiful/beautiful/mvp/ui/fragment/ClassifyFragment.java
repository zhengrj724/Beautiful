package com.beautiful.beautiful.mvp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beautiful.beautiful.R;
import com.beautiful.beautiful.mvp.ui.common.BaseFragment;

/**
 * Created by Mr.R on 2016/9/24.
 */
public class ClassifyFragment extends BaseFragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_classify,container,false);
        return view;
    }

}
