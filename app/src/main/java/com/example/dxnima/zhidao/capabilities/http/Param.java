package com.example.dxnima.zhidao.capabilities.http;

/**
 * <参数类>
 *
 * Created by DXnima on 2019/4/1.
 */
public class Param {
    public Param() {
    }

    public Param(String key, String value) {
        this.key = key;
        this.value = value != null ? value : "";
    }

    public Param(String key, int value) {
        this.key = key;
        this.value = value + "";
    }

    String key;

    String value;
}