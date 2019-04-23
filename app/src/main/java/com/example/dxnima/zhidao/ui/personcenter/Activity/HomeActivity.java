package com.example.dxnima.zhidao.ui.personcenter.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.dxnima.zhidao.R;
import com.example.dxnima.zhidao.bean.table.Msg;
import com.example.dxnima.zhidao.biz.personcenter.IMsgView;
import com.example.dxnima.zhidao.biz.personcenter.MsgPresenter;
import com.example.dxnima.zhidao.ui.base.BaseActivity;
import com.example.dxnima.zhidao.ui.personcenter.Fragment.MainFragment;
import com.example.dxnima.zhidao.ui.personcenter.Fragment.MypageFragment;

import java.util.List;

/**
 * 主页面
 * 对应xml:activity_home.xml
 * Created by DXnima on 2019/4/1.
 */
public class HomeActivity extends BaseActivity implements IMsgView{

    //定义fragment
    private MypageFragment mypageFragment;
    private MainFragment mainFragment;
    // 定义FragmentManager对象管理器
    private FragmentManager fragmentManager;

    /**
     *
     * 底部栏控件
     * */
    private RadioButton home_msg,home_my;

    private ImageView home_sendmsg;

    /**
     * listview控件初始化
     * */

    private FrameLayout fragment_container;

    private MsgPresenter mMsgPresenter;
    private List<Msg> msgList=null;
    /**
     * Activity非正常销毁之后
     * 用onCreate
     * 为了保护我们的数据可以将数据保存在savedInstanceState中
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);
        super.onCreate(savedInstanceState);
        presenter = mMsgPresenter = new MsgPresenter();
        mMsgPresenter.allSendMsg();
        mMsgPresenter.attachView(this);
        fragmentManager = getFragmentManager();
        setChioceItem(0); // 初始化页面加载时显示第一个选项卡
    }

    /**
     * 初始化布局组件
     */
    @Override
    public void initViews(){
        home_msg=(RadioButton) findViewById(R.id.home_msg);
        home_my=(RadioButton) findViewById(R.id.home_my);
        home_sendmsg=(ImageView) findViewById(R.id.home_sendmsg);
        fragment_container=(FrameLayout) findViewById(R.id.fragment_container);
    }

    /**
     * 增加按钮点击事件
     */
    @Override
    public void initListeners(){
        home_msg.setOnClickListener(this);
        home_my.setOnClickListener(this);
        home_sendmsg.setOnClickListener(this);
    }

    /**
     * 点击事件
     * */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_msg:
                mMsgPresenter.allSendMsg();
                setChioceItem(0);
                break;
            case R.id.home_sendmsg:
                startActivity(SendmsgActivity.class, null);
                break;
            case R.id.home_my:
                setChioceItem(1);
                break;
        }
        super.onClick(v);
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData(){
    }

    @Override
    public void onError(String errorMsg, String code){
        showToast(errorMsg);
    }

    @Override
    public void onSuccess(){}

    @Override
    public void showLoading(){}

    @Override
    public void hideLoading(){}

    /**
     * 设置点击选项卡的事件处理
     *
     * @param index 选项卡的标号：0, 1
     */
    private void setChioceItem(int index) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFragments(fragmentTransaction);//隐藏所以fragment
        switch (index) {
            case 0:
                // 如果为空，则创建一个并添加到界面上
                if (mainFragment == null) {
                    mainFragment = new MainFragment();
                    fragmentTransaction.add(R.id.fragment_container, mainFragment);
                } else {
                    // 如果不为空，则直接将它显示出来
                    fragmentTransaction.show(mainFragment);
                }
                break;
            case 1:
                if (mypageFragment == null) {
                    mypageFragment = new MypageFragment();
                    fragmentTransaction.add(R.id.fragment_container, mypageFragment);
                } else {
                    fragmentTransaction.show(mypageFragment);
                }
                break;
        }
        fragmentTransaction.commit(); // 提交
    }

    /**
     * 隐藏Fragment
     *
     * @param fragmentTransaction
     */
    private void hideFragments(FragmentTransaction fragmentTransaction) {
        if (mainFragment !=null){
            fragmentTransaction.hide(mainFragment);
        }
        if (mypageFragment!=null){
            fragmentTransaction.hide(mypageFragment);
        }
    }

}