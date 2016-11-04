package com.beautiful.beautiful.mvp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beautiful.beautiful.R;
import com.beautiful.beautiful.mvp.ui.common.BaseFragment;
import com.beautiful.beautiful.utils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Mr.R on 2016/9/24.
 */
public class ClassifyFragment extends BaseFragment {

    @Bind(R.id.tb_classify)
    Toolbar tbClassify;
    @Bind(R.id.tv_classify0)
    TextView tvClassify0;
    @Bind(R.id.tv_classify1)
    TextView tvClassify1;
    @Bind(R.id.tv_classify2)
    TextView tvClassify2;
    @Bind(R.id.tv_classify3)
    TextView tvClassify3;
    @Bind(R.id.tv_classify4)
    TextView tvClassify4;
    @Bind(R.id.tv_classify5)
    TextView tvClassify5;
    @Bind(R.id.tv_classify6)
    TextView tvClassify6;
    @Bind(R.id.tv_classify7)
    TextView tvClassify7;
    @Bind(R.id.tv_classify8)
    TextView tvClassify8;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_classify, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_classify0, R.id.tv_classify1, R.id.tv_classify2, R.id.tv_classify3,
            R.id.tv_classify4, R.id.tv_classify5, R.id.tv_classify6, R.id.tv_classify7,
            R.id.tv_classify8})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_classify0:
                ToastUtil.ShortToast(activity, "classify0");
                break;
            case R.id.tv_classify1:
                ToastUtil.ShortToast(activity, "classify1");
                break;
            case R.id.tv_classify2:
                ToastUtil.ShortToast(activity, "classify2");
                break;
            case R.id.tv_classify3:
                ToastUtil.ShortToast(activity, "classify3");
                break;
            case R.id.tv_classify4:
                ToastUtil.ShortToast(activity, "classify4");
                break;
            case R.id.tv_classify5:
                ToastUtil.ShortToast(activity, "classify5");
                break;
            case R.id.tv_classify6:
                ToastUtil.ShortToast(activity, "classify6");
                break;
            case R.id.tv_classify7:
                ToastUtil.ShortToast(activity, "classify7");
                break;
            case R.id.tv_classify8:
                ToastUtil.ShortToast(activity, "classify8");
                break;
            default:
                break;
        }
    }
}
