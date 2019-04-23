package com.example.dxnima.zhidao.bean.table;

import com.example.dxnima.zhidao.bean.BaseResp;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 用户登陆表
 * Created by DXnima on 2019/4/2.
 */
@Entity
public class User  extends BaseResp<User> {

    private Integer userid;

    private String username;

    private String password;

    private String email;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserid() {
        return this.userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Generated(hash = 51566870)
    public User(Integer userid, String username, String password, String email) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Generated(hash = 586692638)
    public User() {
    }

}