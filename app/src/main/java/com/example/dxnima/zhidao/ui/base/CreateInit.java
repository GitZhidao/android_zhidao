package com.example.dxnima.zhidao.ui.base;

/**
 *<公共方法抽象>
 * Created by DXnima on 2019/4/1.
 */
public interface CreateInit {
    /**
     * 初始化布局组件
     */
    public void initViews();

    /**
     * 增加按钮点击事件
     */
    void initListeners();

    /**
     * 初始化数据
     */
    public void initData();

    /**
     * 初始化公共头部
     */
    public void setHeader();

}
