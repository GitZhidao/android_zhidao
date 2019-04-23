package com.example.dxnima.zhidao.ui.base;

import android.view.View;

/**
 * 具体实现是在PresentationLayerFuncHelper里面
 * Created by DXnima on 2019/4/1.
 */
public interface PresentationLayerFunc {
    /**
     * 弹出消息
     *
     * @param msg
     */
    public void showToast(String msg);

    /**
     * 网络请求加载框
     */
    public void showProgressDialog();

    /**
     * 隐藏网络请求加载框
     */
    public void hideProgressDialog();

    /**
     * 显示软键盘
     *
     * @param focusView
     */
    public void showSoftKeyboard(View focusView);

    /**
     * 隐藏软键盘
     */
    public void hideSoftKeyboard();
}
