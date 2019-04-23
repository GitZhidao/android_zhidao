package com.example.dxnima.zhidao.biz;

/**
 *  * <功能详细描述>
 *      IMvpView接口实现跟activity的交互
 * Created by DXnima on 2019/4/1.
 */
public interface IMvpView {
    void onError(String errorMsg, String code);

    void onSuccess();

    void showLoading();

    void hideLoading();
}
