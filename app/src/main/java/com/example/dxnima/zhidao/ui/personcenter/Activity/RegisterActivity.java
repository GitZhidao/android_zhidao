package com.example.dxnima.zhidao.ui.personcenter.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dxnima.zhidao.R;
import com.example.dxnima.zhidao.biz.personcenter.InterfaceView.IUserLoginView;
import com.example.dxnima.zhidao.biz.personcenter.UserPresenter;
import com.example.dxnima.zhidao.constant.Event;
import com.example.dxnima.zhidao.ui.base.BaseActivity;

/**
 * 注册页面
 * 对应xml:activity_register
 * Created by DXnima on 2019/4/3.
 */
public class RegisterActivity extends BaseActivity implements IUserLoginView{

    private EditText username;//用户名
    private EditText password;//密码
    private EditText email;//邮箱
    private EditText getCode;//验证码
    private Button register;//注册
    private TextView textView;//获取验证码
    private TextView login;//已有账号去登陆

    private UserPresenter mUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        setHeader();
        super.onCreate(savedInstanceState);
        presenter = mUserPresenter = new UserPresenter();
        mUserPresenter.attachView(this);
    }


    @Override
    public void initViews() {
        username=(EditText) findViewById(R.id.register_username);
        password=(EditText) findViewById(R.id.register_password);
        email=(EditText) findViewById(R.id.register_email);
        getCode=(EditText) findViewById(R.id.register_getCode);
        register=(Button) findViewById(R.id.register_btn);
        textView=(TextView) findViewById(R.id.register_setcode);
        login=(TextView) findViewById(R.id.register_login);
    }

    // 初始化公共头部
    @Override
    public void setHeader() {
        super.setHeader();
        title.setText("注册");
    }

    @Override
    public void initListeners() {
        register.setOnClickListener(this);
        textView.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void clearEditContent() {
        username.setText("");
        password.setText("");
        email.setText("");
        getCode.setText("");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_btn:
                mUserPresenter.registerInternet(username.getText().toString(), password.getText().toString(),email.getText().toString());
                break;
            case R.id.register_setcode:
                //TODO 获取验证码
                break;
            case R.id.register_login:
                startActivity(LoginActivity.class,null);
                break;
        }
        super.onClick(v);
    }

    @Override
    public void onError(String errorMsg, String code) {
        showToast(errorMsg);
    }

    @Override
    public void onSuccess() {
        startActivity(LoginActivity.class,null);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}