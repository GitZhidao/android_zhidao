package com.example.dxnima.zhidao.ui.personcenter.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dxnima.zhidao.R;
import com.example.dxnima.zhidao.biz.personcenter.InterfaceView.IMsgView;
import com.example.dxnima.zhidao.biz.personcenter.MsgPresenter;
import com.example.dxnima.zhidao.ui.base.BaseActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Set;

/**
 * 主题发送页面
 * 对应xml:activity_addmsg
 * Created by DXnima on 2019/4/11.
 */
public class SendmsgActivity extends BaseActivity implements IMsgView{

    private MsgPresenter mMsgPresenter;
    private TagFlowLayout gltags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_addmsg);
        super.onCreate(savedInstanceState);
        setHeader();
        presenter = mMsgPresenter = new MsgPresenter();
        mMsgPresenter.attachView(this);
    }

    @Override
    public void initViews() {
        gltags=(TagFlowLayout) findViewById(R.id.addmsg_gl_tags);
        addTag();
    }

    // 初始化公共头部
    @Override
    public void setHeader() {
        super.setHeader();
        title.setText("发送通知");
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onError(String errorMsg, String code) {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


   public String[] mVals = new String[]
            {"尼玛", "王尼玛", "往往尼玛", "马尼", "你你你", "吗","马马马", "王吗"};

    //添加标签
    public void addTag(){

        final LayoutInflater mInflater = LayoutInflater.from(SendmsgActivity.this);
        gltags.setAdapter(new TagAdapter<String>(mVals)
        {
            @Override
            public View getView(FlowLayout parent, int position, String s)
            {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv, gltags, false);
                tv.setText(s);
                return tv;
            }
        });
        gltags.setOnTagClickListener(new TagFlowLayout.OnTagClickListener()
        {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent)
            {
                Toast.makeText(SendmsgActivity.this, mVals[position], Toast.LENGTH_SHORT)
                        .show();
                //view.setVisibility(View.GONE);
                return true;
            }
        });
    }
}