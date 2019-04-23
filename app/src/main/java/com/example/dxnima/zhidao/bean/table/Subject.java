package com.example.dxnima.zhidao.bean.table;

import com.example.dxnima.zhidao.bean.ListBaseResp;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by DXnima on 2019/4/19.
 */
@Entity
public class Subject extends ListBaseResp<Subject>{
    private int subid; //id

    private String subtitle;//主题标题

    private int userid;//用户id

    private String code;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getUserid() {
        return this.userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getSubid() {
        return this.subid;
    }

    public void setSubid(int subid) {
        this.subid = subid;
    }

    @Generated(hash = 1166054311)
    public Subject(int subid, String subtitle, int userid, String code) {
        this.subid = subid;
        this.subtitle = subtitle;
        this.userid = userid;
        this.code = code;
    }

    @Generated(hash = 1617906264)
    public Subject() {
    }
}