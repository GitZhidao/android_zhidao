package com.example.dxnima.zhidao.ui.personcenter.Activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.dxnima.zhidao.R;
import com.example.dxnima.zhidao.biz.personcenter.GetSubjectPresenter;
import com.example.dxnima.zhidao.biz.personcenter.InterfaceView.IGetSubjectView;
import com.example.dxnima.zhidao.ui.base.BaseActivity;
import com.example.dxnima.zhidao.ui.personcenter.Fragment.MainFragment;
import com.example.dxnima.zhidao.ui.personcenter.Fragment.MypageFragment;
import com.example.dxnima.zhidao.util.ToastUtil;

/**
 * 主页面
 * 对应xml:activity_home.xml
 * Created by DXnima on 2019/4/1.
 */
public class HomeActivity extends BaseActivity implements IGetSubjectView{

    //定义fragment
    private MypageFragment mypageFragment;
    private MainFragment mainFragment;
    // 定义FragmentManager对象管理器
    private FragmentManager fragmentManager;

    //定义一个变量，来标识是否退出
    private static boolean isExit=false;
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

    private GetSubjectPresenter getSubjectPresenter;

    /**
     * Activity非正常销毁之后
     * 用onCreate
     * 为了保护我们的数据可以将数据保存在savedInstanceState中
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);
        setMenu();//添加悬浮按钮
        super.onCreate(savedInstanceState);
        presenter = getSubjectPresenter = new GetSubjectPresenter();
        getSubjectPresenter.attachView(this);
        fragmentManager = getSupportFragmentManager();
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

    /**
     * 按两次返回键退出
     * */
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(!isExit) {
                isExit = true;
                ToastUtil.makeText(this,"再按一次退出");
            }
            else{
                finish();
                System.exit(0);
            }
            return false;
        }
        return super.onKeyDown(keyCode,event);
    }

    @Override
    public void onError(String errorMsg, String code){
        showToast(errorMsg);
    }

    @Override
    public void onSuccess(){
    }

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