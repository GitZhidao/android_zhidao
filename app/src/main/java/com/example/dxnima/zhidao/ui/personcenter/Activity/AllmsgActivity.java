package com.example.dxnima.zhidao.ui.personcenter.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.dxnima.zhidao.R;
import com.example.dxnima.zhidao.bean.table.Msg;
import com.example.dxnima.zhidao.biz.personcenter.InterfaceView.IMsgView;
import com.example.dxnima.zhidao.biz.personcenter.MsgPresenter;
import com.example.dxnima.zhidao.ui.base.BaseActivity;
import com.example.dxnima.zhidao.view.MyListViewAdapter;
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

    private MyListViewAdapter mAdapter = null;
    private List<MyListViewData> mData = null;
    private List<Msg> msgList=null;
    private MsgPresenter mMsgPresenter;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_allmsg);
        super.onCreate(savedInstanceState);
        setHeader();
        presenter = mMsgPresenter = new MsgPresenter();
        mMsgPresenter.attachView(this);
        Bundle bundle;
        bundle=getIntent().getExtras();
        mMsgPresenter.allMsgByCode(bundle.getString("code"));
    }

    @Override
    public void initViews() {
        bundle=new Bundle();
        mData = new LinkedList<MyListViewData>();
        mAdapter = new MyListViewAdapter((LinkedList<MyListViewData>) mData,this);
        searchMsg=(SearchView) findViewById(R.id.allmsg_searchMsg);
        listMsg=(ListView) findViewById(R.id.allmsg_listMsg);
        allMsg_text=(TextView) findViewById(R.id.allmsg_text);
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
        }
        super.onClick(v);
    }

    @Override
    public void initData() {
    }

    @Override
    public void onError(String errorMsg, String code) {

    }

    @Override
    public void onSuccess() {
        addListViewdata();
        itemOclik();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    public void addListViewdata(){
        Msg msg;
        msgList=mMsgPresenter.msgList;
        if (msgList==null) return;
        else
            for (int i = 0; i < msgList.size(); i++) {
                msg = msgList.get(i);
                mAdapter.add(new MyListViewData(R.mipmap.five_star, msg.getTitle(),"结束时间：",msg
                        .getEndtime()));
            }
    }

    //对应item的点击事件
    public void itemOclik(){
        for (int position = 0; position <mData.size(); position++) {
            //item的点击事件，里面可以设置跳转并传值
            listMsg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MyListViewData data=mData.get(position);
                    Intent intent = new Intent(AllmsgActivity.this,SeemsgActivity.class);//跳转到看消息界面
                    bundle.putString("title", data.getTitle());//bundle传值，SeemsgActivity中使用
                    bundle.putString("endtime", data.getEndtime());
                    bundle.putString("content",msgList.get(position).getContent());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
    }
}