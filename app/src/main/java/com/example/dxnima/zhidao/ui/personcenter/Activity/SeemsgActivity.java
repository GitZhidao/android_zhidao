package com.example.dxnima.zhidao.ui.personcenter.Activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.dxnima.zhidao.R;
import com.example.dxnima.zhidao.biz.personcenter.IMsgView;
import com.example.dxnima.zhidao.biz.personcenter.MsgPresenter;
import com.example.dxnima.zhidao.ui.base.BaseActivity;

/**
 * 查看通知
 * xml:activity_seemsg.xml
 * Created by DXnima on 2019/4/13.
 */
public class SeemsgActivity extends BaseActivity implements IMsgView {

    private TextView msgTitel,msgText;

    private WebView map;

    private MsgPresenter mMsgPresenter;
    private Bundle bundle;
    /**
     * Activity非正常销毁之后
     * 用onCreate
     * 为了保护我们的数据可以将数据保存在savedInstanceState中
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_seemsg);
        super.onCreate(savedInstanceState);
        setHeader();
        presenter = mMsgPresenter = new MsgPresenter();
        mMsgPresenter.attachView(this);
    }

    @Override
    public void initViews() {
        msgTitel = (TextView) findViewById(R.id.msgTitel);
        msgText = (TextView) findViewById(R.id.msgText);
        map = (WebView) findViewById(R.id.map);
        bundle=getIntent().getExtras();
        msgTitel.setText(bundle.getString("title"));
        msgText.setText(bundle.getString("content"));
    }

    // 初始化公共头部
    @Override
    public void setHeader() {
        super.setHeader();
        title.setText(bundle.getString("title"));
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