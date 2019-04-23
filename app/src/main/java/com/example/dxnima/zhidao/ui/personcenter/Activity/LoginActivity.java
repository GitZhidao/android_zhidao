package com.example.dxnima.zhidao.ui.personcenter.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dxnima.zhidao.R;
import com.example.dxnima.zhidao.biz.personcenter.IUserLoginView;
import com.example.dxnima.zhidao.biz.personcenter.MsgPresenter;
import com.example.dxnima.zhidao.biz.personcenter.UserPresenter;
import com.example.dxnima.zhidao.constant.Event;
import com.example.dxnima.zhidao.ui.base.BaseActivity;

/**
 * 登录界面（LoginActivity），只要继承BaseActivity则可以了
 * 对应xml:activity_login.xml
 * Created by DXnima on 2019/4/1.
 */
public class LoginActivity extends BaseActivity implements IUserLoginView{

    /**
     *页面 用户名
     */
    private EditText userName;

    /**
     * 页面用户密码
     */
    private EditText password;

    /**
     * 页面登录按钮
     */
    private Button login;

    /**注册*/
    private TextView go_register;

    /**忘记密码？*/
    private TextView go_updatepassword;

    private UserPresenter mUserPresenter;

    /**
     * Activity非正常销毁之后
     * 用onCreate
     * 为了保护我们的数据可以将数据保存在savedInstanceState中
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        setHeader();
        super.onCreate(savedInstanceState);
        presenter = mUserPresenter = new UserPresenter();
        mUserPresenter.attachView(this);
    }

    /**
     * 初始化布局组件
     */
    @Override
    public void initViews() {
        userName = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        go_register=(TextView) findViewById(R.id.go_register);
        go_updatepassword=(TextView) findViewById(R.id.go_updatepassword);
    }


    /**
     * 添加按钮点击事件
     * 有点击事件就在onclick实现
     * */
    @Override
    public void initListeners() {
        login.setOnClickListener(this);
        go_register.setOnClickListener(this);
        go_updatepassword.setOnClickListener(this);
    }

    /**
     * 点击事件
     * */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:mUserPresenter.loginInternet(userName.getText().toString(), password.getText().toString());//登陆
                break;
            case R.id.go_register:
                startActivity(RegisterActivity.class,null);//点击注册跳转注册界面
                break;
            case R.id.go_updatepassword:
                startActivity(UpdatepsdActivity.class,null);//点击修改密码跳转修改密码界面
                break;
        }
        super.onClick(v);
    }

    //初始化数据
    @Override
    public void initData() {

    }

    // 初始化公共头部
    @Override
    public void setHeader() {
        super.setHeader();
        title.setText("登录");
    }

    /**
     * 使用onEventMainThread作为订阅函数，那么不论事件是在哪个线程中发布出来的，
     * onEventMainThread都会在UI线程中执行，接收事件就会在UI线程中运行
     * */
    @Override
    public void onEventMainThread(Event event) {
        super.onEventMainThread(event);
        switch (event){
            case IMAGE_LOADER_SUCCESS:
                clearEditContent();
                break;
        }
    }

    /**
     *控件内容初始化
     * */
    @Override
    public void clearEditContent() {
        userName.setText("");
        password.setText("");
    }

    @Override
    public void onError(String errorMsg, String code) {
        showToast(errorMsg);
    }


    @Override
    public void onSuccess() {
        MsgPresenter mMsgPresenter=new MsgPresenter();
        mMsgPresenter.allSendMsg();
        //成功打开新界面homeactivity 主界面
        Intent intent = new Intent(this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的 activity
        startActivity(intent);
        this.finish();//完成登陆注册等开始界面的销毁
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}