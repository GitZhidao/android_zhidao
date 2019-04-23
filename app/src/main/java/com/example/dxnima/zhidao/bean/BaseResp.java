package com.example.dxnima.zhidao.bean;

import com.google.gson.annotations.SerializedName;

/**
 * <网络请求返回体>
 * Created by DXnima on 2019/4/1.
 */
public class BaseResp<T> {

    private String msg;

    private String success;

    private int status;

    @SerializedName("data")
    private T dataList;

    public T getData() {
        return dataList;
    }

    public void setData(T data) {
        this.dataList = data;
    }


    public int getstatus() {
        return status;
    }

    public void setstatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}