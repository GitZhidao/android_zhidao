package com.example.dxnima.zhidao.biz;

/**
 * <基础业务类>
 * Created by DXnima on 2019/4/1.
 */
public interface Presenter<V> {
    void attachView(V view);

    void detachView(V view);

    String getName();
}
