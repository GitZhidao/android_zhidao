package com.example.dxnima.zhidao.ui.personcenter.Activity;

import android.os.Bundle;

import com.example.dxnima.zhidao.R;
import com.example.dxnima.zhidao.biz.personcenter.IMsgView;
import com.example.dxnima.zhidao.biz.personcenter.MsgPresenter;
import com.example.dxnima.zhidao.ui.base.BaseActivity;

/**
 * 主题发送页面
 * 对应xml:activity_addmsg
 * Created by DXnima on 2019/4/11.
 */
public class SendmsgActivity extends BaseActivity implements IMsgView{

    private MsgPresenter mMsgPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_addmsg);
        super.onCreate(savedInstanceState);
        setHeader();
        presenter = mMsgPresenter = new MsgPresenter();
        mMsgPresenter.attachView(this);
    }

    @Override
    public void initViews() {

    }

    // 初始化公共头部
    @Override
    public void setHeader() {
        super.setHeader();
        title.setText("发送通知");
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
}