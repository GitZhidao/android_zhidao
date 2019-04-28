package com.example.dxnima.zhidao.view;

import android.view.View;

import com.zhy.view.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 标签适配器 tag
 * Created by DXnima on 2019/4/28.
 */

public abstract class MyTagAdapter<T> {

    private List<T> mTagDatas;
    private OnDataChangedListener mOnDataChangedListener;

    public MyTagAdapter(List<T> datas) {
        mTagDatas = datas;
    }


    public MyTagAdapter(T[] datas) {
        mTagDatas = new ArrayList<T>(Arrays.asList(datas));
    }


    static interface OnDataChangedListener {
        void onChanged();
    }

    void setOnDataChangedListener(OnDataChangedListener listener) {
        mOnDataChangedListener = listener;
    }

    public int getCount() {
        return mTagDatas == null ? 0 : mTagDatas.size();
    }

    public void notifyDataChanged() {
        mOnDataChangedListener.onChanged();
    }

    public T getItem(int position) {
        return mTagDatas.get(position);
    }

    public abstract View getView(FlowLayout parent, int position, T t);

}
