package com.example.dxnima.zhidao.ui.base;

import android.content.Context;
import android.view.View;

import com.example.dxnima.zhidao.util.ToastUtil;

/**
 * <页面基础公共功能实现>
 * Created by DXnima on 2019/4/1.
 */
public class PresentationLayerFuncHelper implements PresentationLayerFunc{
    private Context context;

    public PresentationLayerFuncHelper(Context context) {
        this.context = context;
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.makeText(context, msg);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void showSoftKeyboard(View focusView) {

    }

    @Override
    public void hideSoftKeyboard() {

    }
}