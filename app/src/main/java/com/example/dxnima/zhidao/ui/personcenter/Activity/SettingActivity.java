package com.example.dxnima.zhidao.ui.personcenter.Activity;

import android.os.Bundle;

import com.example.dxnima.zhidao.R;
import com.example.dxnima.zhidao.biz.personcenter.IUserLoginView;
import com.example.dxnima.zhidao.biz.personcenter.UserPresenter;
import com.example.dxnima.zhidao.ui.base.BaseActivity;

/**
 * 设置页面
 * 对应xml:activity_setting
 * Created by DXnima on 2019/4/11.
 */
public class SettingActivity extends BaseActivity implements IUserLoginView {

    private UserPresenter mUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_setting);
        super.onCreate(savedInstanceState);
        setHeader();
        presenter = mUserPresenter = new UserPresenter();
        mUserPresenter.attachView(this);
    }
    @Override
    public void initViews() {

    }

    // 初始化公共头部
    @Override
    public void setHeader() {
        super.setHeader();
        title.setText("设置");
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onError(String errorMsg, String code) {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void clearEditContent() {

    }
}