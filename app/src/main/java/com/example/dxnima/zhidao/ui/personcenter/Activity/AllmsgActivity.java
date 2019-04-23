package com.example.dxnima.zhidao.ui.personcenter.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.dxnima.zhidao.R;
import com.example.dxnima.zhidao.biz.personcenter.IMsgView;
import com.example.dxnima.zhidao.biz.personcenter.MsgPresenter;
import com.example.dxnima.zhidao.ui.base.BaseActivity;
import com.example.dxnima.zhidao.view.MyAdapter;
import com.example.dxnima.zhidao.view.MyListViewData;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by DXnima on 2019/4/21.
 */
public class AllmsgActivity extends BaseActivity implements IMsgView{

    private SearchView searchMsg;
    private ListView listMsg;
    private TextView allMsg_text;

    private MyAdapter mAdapter = null;
    private List<MyListViewData> mData = null;
    private MsgPresenter mMsgPresenter;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_allmsg);
        super.onCreate(savedInstanceState);
        setHeader();
        presenter = mMsgPresenter = new MsgPresenter();
        mMsgPresenter.attachView(this);
    }

    @Override
    public void initViews() {
        bundle=new Bundle();
        mData = new LinkedList<MyListViewData>();
        mAdapter = new MyAdapter((LinkedList<MyListViewData>) mData,this);
        searchMsg=(SearchView) findViewById(R.id.searchMsg);
        listMsg=(ListView) findViewById(R.id.listMsg);
        allMsg_text=(TextView) findViewById(R.id.allmsg_text);
        mAdapter.add(new MyListViewData(R.drawable.xingxing,"英语考试","结束时间：","10：00"));
        listMsg.setAdapter(mAdapter);
        allMsg_text.setText("暂无通知~");
        listMsg.setEmptyView(allMsg_text);
    }

    // 初始化公共头部
    @Override
    public void setHeader() {
        super.setHeader();
        title.setText("通知");
    }

    @Override
    public void initListeners() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_msg:
                break;
        }
        super.onClick(v);
    }

    @Override
    public void initData() {
        for (int position = 0; position <mData.size(); position++) {
            //item的点击事件，里面可以设置跳转并传值
            listMsg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MyListViewData data=mData.get(position);
                    Intent intent = new Intent(AllmsgActivity.this,SeemsgActivity.class);//跳转到看消息界面
                    bundle.putString("title", data.getTitle());//bundle传值，SeemsgActivity中使用
                    bundle.putString("endtime", data.getEndtime());
                    bundle.putString("content","B210");
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
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
}