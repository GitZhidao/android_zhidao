package com.example.dxnima.zhidao.constant;

import com.squareup.okhttp.MediaType;

/**
 * <全局常量>
 * Created by DXnima on 2019/4/1.
 */
public class Constants {
    /**
     * 返回请求成功码
     */
    public static final String SUCESS_CODE_END = "000000";

    /**
     * 返回请求失败码
     */
    public static final String FAILED_CODE_END = "000001";

    /**
     * 魔鬼数字
     */
    public static final int NUM0 = 0;

    public static final int NUM1 = 1;

    public static final int NUM2 = 2;

    public static final int NUM3 = 3;

    public static final int NUM4 = 4;

    /**
     * 请求失败展示信息
     */
    public static final String ERROR_MESSAGE = "请求失败，请稍后再试";

    /**json*/
    public static MediaType JSON=MediaType.parse("application/json; charset=utf-8");
}