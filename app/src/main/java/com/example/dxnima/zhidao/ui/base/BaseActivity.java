package com.example.dxnima.zhidao.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dxnima.zhidao.R;
import com.example.dxnima.zhidao.ZDApplication;
import com.example.dxnima.zhidao.biz.BasePresenter;
import com.example.dxnima.zhidao.biz.IMvpView;
import com.example.dxnima.zhidao.bridge.BridgeFactory;
import com.example.dxnima.zhidao.bridge.Bridges;
import com.example.dxnima.zhidao.bridge.http.OkHttpManager;
import com.example.dxnima.zhidao.constant.Event;

import de.greenrobot.event.EventBus;

/**
 * <基础activity>
 *  activity 常用行为
 *  activity生命周期
 * Created by DXnima on 2019/4/1.
 */
public abstract class BaseActivity extends Activity implements CreateInit, PublishActivityCallBack, PresentationLayerFunc, IMvpView, View.OnClickListener {

    private PresentationLayerFuncHelper presentationLayerFuncHelper;

    /**
     * 返回按钮
     */
    private LinearLayout back;

    /**
     * 标题，右边字符
     */
    protected TextView title, right;

    public BasePresenter presenter;

    public final String TAG = this.getClass().getSimpleName();

    /**
     * Activity非正常销毁之后
     * 用onCreate
     * 为了保护我们的数据可以将数据保存在savedInstanceState中
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presentationLayerFuncHelper = new PresentationLayerFuncHelper(this);
        initViews();
        initListeners();
        initData();
        ZDApplication.zdApplication.addActivity(this);
        EventBus.getDefault().register(this);
    }

    /**
     * 初始化公共头部
     */
    @Override
    public void setHeader() {
        back = (LinearLayout) findViewById(R.id.ll_back);
        title = (TextView) findViewById(R.id.tv_title);
        right = (TextView) findViewById(R.id.tv_right);
        back.setOnClickListener(this);
    }


    /**
     * 点击事件
     * */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }

    /**
     * 使用onEventMainThread作为订阅函数，那么不论事件是在哪个线程中发布出来的，
     * onEventMainThread都会在UI线程中执行，接收事件就会在UI线程中运行
     * */
    public void onEventMainThread(Event event) {

    }

    /**
     * activity获得用户焦点，在与用户交互
     * */
    @Override
    protected void onResume() {
        ZDApplication.zdApplication.currentActivityName = this.getClass().getName();
        super.onResume();
    }

    @Override
    public void startActivity(Class<?> openClass, Bundle bundle) {
        Intent intent = new Intent(this, openClass);
        if (null != bundle)
            intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void openActivityForResult(Class<?> openClass, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, openClass);
        if (null != bundle)
            intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void setResultOk(Bundle bundle) {
        Intent intent = new Intent();
        if (bundle != null) ;
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void showToast(String msg) {
        presentationLayerFuncHelper.showToast(msg);
    }

    @Override
    public void showProgressDialog() {
        presentationLayerFuncHelper.showProgressDialog();
    }

    @Override
    public void hideProgressDialog() {
        presentationLayerFuncHelper.hideProgressDialog();
    }

    @Override
    public void showSoftKeyboard(View focusView) {
        presentationLayerFuncHelper.showSoftKeyboard(focusView);
    }

    @Override
    public void hideSoftKeyboard() {
        presentationLayerFuncHelper.hideSoftKeyboard();
    }

    @Override
    protected void onDestroy() {
        ZDApplication.zdApplication.deleteActivity(this);
        EventBus.getDefault().unregister(this);
        if (presenter != null) {
            presenter.detachView(this);
        }
        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);
        httpManager.cancelActivityRequest(TAG);
        super.onDestroy();
    }

}