package com.example.dxnima.zhidao.biz;

/**
 * <基础业务类>
 * Created by DXnima on 2019/4/1.
 */
public abstract class BasePresenter<V extends IMvpView> implements Presenter<V> {
    protected V mvpView;

    public void attachView(V view) {
        mvpView = view;
    }

    @Override
    public void detachView(V view) {
        mvpView = null;
    }

    @Override
    public String getName() {
        return mvpView.getClass().getSimpleName();
    }
}