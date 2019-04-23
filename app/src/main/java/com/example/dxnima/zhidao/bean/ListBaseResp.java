package com.example.dxnima.zhidao.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DXnima on 2019/4/16.
 */
public class ListBaseResp<T> {
    private String msg;

    private String success;

    private int status;

    @SerializedName("data")
    private List<T> dataList;

    public List<T> getData()
    {
        return dataList;
    }

    public void setData(List<T> data)
    {
        this.dataList = data;
    }


    public int getstatus()
    {
        return status;
    }

    public void setstatus(int status)
    {
        this.status = status;
    }

    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg){
        this.msg=msg;
    }

    public String getSuccess(){
        return success;
    }

    public void setSuccess(String success){
        this.success=success;
    }
}