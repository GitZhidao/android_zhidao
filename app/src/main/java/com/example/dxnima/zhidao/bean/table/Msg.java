package com.example.dxnima.zhidao.bean.table;

import com.example.dxnima.zhidao.bean.ListBaseResp;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Entity
 * Created by DXnima on 2019/4/9.
 */
@Entity
public class Msg extends ListBaseResp<Msg> {
    private Integer msgid;

    private String title;

    private String endtime;

    private String creattime;

    private String location;

    private String filepath;

    private String content;

    private Integer subid;

    public Integer getSubid() {
        return this.subid;
    }

    public void setSubid(Integer subid) {
        this.subid = subid;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilepath() {
        return this.filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreattime() {
        return this.creattime;
    }

    public void setCreattime(String creattime) {
        this.creattime = creattime;
    }

    public String getEndtime() {
        return this.endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMsgid() {
        return this.msgid;
    }

    public void setMsgid(Integer msgid) {
        this.msgid = msgid;
    }

    @Generated(hash = 882773778)
    public Msg(Integer msgid, String title, String endtime, String creattime,
            String location, String filepath, String content, Integer subid) {
        this.msgid = msgid;
        this.title = title;
        this.endtime = endtime;
        this.creattime = creattime;
        this.location = location;
        this.filepath = filepath;
        this.content = content;
        this.subid = subid;
    }

    @Generated(hash = 23037457)
    public Msg() {
    }

}