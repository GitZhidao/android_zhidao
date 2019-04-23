package com.example.dxnima.zhidao.ui.personcenter.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.dxnima.zhidao.R;
import com.example.dxnima.zhidao.ui.base.BaseActivity;

/**
 * 欢迎界面
 * 对应xml:common_lunch.xml
 * Created by DXnima on 2019/4/3.
 */
public class WelcomeActivity extends BaseActivity{

    private final int SPLASH_DISPLAY_LENGHT = 1000;  //延迟1秒

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_lunch);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);//跳转到登陆页面
                WelcomeActivity.this.startActivity(intent);
                WelcomeActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }

    @Override
    public void initViews() {
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