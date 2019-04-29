package com.example.dxnima.zhidao.view.ListView;

/**
 * Created by DXnima on 2019/4/29.
 */

public class MypageListViewData {
    private String title;//标题
    private String endtime;//结束时间
    private String text;//内容

    public MypageListViewData() {}

    public MypageListViewData(String title,String text ,String endtime) {
        this.title = title;
        this.endtime=endtime;
        this.text=text;
    }

    public String getTitle() {
        return title;
    }

    public String getEndtime(){ return endtime;}

    public String getText(){return text;}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEndtime(String endtime){this.endtime=endtime;}

    public void setText(String text){this.text=text;}
}
