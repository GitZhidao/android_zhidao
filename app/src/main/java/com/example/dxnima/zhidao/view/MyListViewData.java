package com.example.dxnima.zhidao.view;

/**
 * Created by Jay on 2015/9/21 0021.
 */
public class MyListViewData {
    private int imgId;//通知图片
    private String title;//标题
    private String endtime;//结束时间
    private String text;//

    public MyListViewData() {}

    public MyListViewData(int imgId, String title,String text ,String endtime) {
        this.imgId = imgId;
        this.title = title;
        this.endtime=endtime;
        this.text=text;
    }

    public int getImgId() {
        return imgId;
    }

    public String getTitle() {
        return title;
    }

    public String getEndtime(){ return endtime;}

    public String getText(){return text;}

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEndtime(String endtime){this.endtime=endtime;}

    public void setText(String text){this.text=text;}
}

