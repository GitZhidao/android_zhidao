package com.example.dxnima.zhidao.ui.personcenter.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dxnima.zhidao.R;
import com.example.dxnima.zhidao.bean.table.Msg;
import com.example.dxnima.zhidao.bean.table.Subject;
import com.example.dxnima.zhidao.biz.personcenter.InterfaceView.IMsgView;
import com.example.dxnima.zhidao.biz.personcenter.MsgPresenter;
import com.example.dxnima.zhidao.biz.personcenter.SubjectPresenter;
import com.example.dxnima.zhidao.capabilities.log.EBLog;
import com.example.dxnima.zhidao.ui.personcenter.Activity.AllmsgActivity;
import com.example.dxnima.zhidao.ui.personcenter.Activity.SeemsgActivity;
import com.example.dxnima.zhidao.view.ListView.MyListViewData;
import com.example.dxnima.zhidao.view.ListView.MypageListViewAdapter;
import com.example.dxnima.zhidao.view.ListView.MypageListViewData;

import java.util.LinkedList;
import java.util.List;

/**
 * 关注
 * Created by DXnima on 2019/4/29.
 */

public class MypageSubjectFragment extends Fragment{

    private ListView sujectListView;
    private MypageListViewAdapter mAdapter = null;
    private List<MypageListViewData> mData = null;
    private MsgPresenter msgPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData = new LinkedList<MypageListViewData>();
        mAdapter = new MypageListViewAdapter((LinkedList<MypageListViewData>) mData,getActivity());
        msgPresenter=new MsgPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mypage_suject, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        sujectListView=(ListView) view.findViewById(R.id.mypage_suject_listView);
        bindViews();
        initData();
        itemOclik();
    }

    private void bindViews() {
        sujectListView.setAdapter(mAdapter);
    }


    private void initData(){
        SubjectPresenter subjectPresenter=new SubjectPresenter();
        List<Subject> subjectList=subjectPresenter.subjectList;
        List<Msg> msgList;
        Msg msg;
        if (subjectList==null) return;
        for (int i=0;i<subjectList.size();i++) {
            msgList=msgPresenter.testMsgByCode(subjectList.get(i).getCode());
            if (msgList == null) continue;
            for (int j = 0; j < msgList.size(); j++) {
                msg = msgList.get(j);
                mAdapter.add(new MypageListViewData(msg.getTitle(), msg.getContent(), "结束时间：" + msg.getEndtime()));
            }
        }
    }

    //对应item的点击事件
    public void itemOclik(){
        for (int position = 0; position <mData.size(); position++) {
            //item的点击事件，里面可以设置跳转并传值
            sujectListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MypageListViewData data=mData.get(position);
                    Intent intent = new Intent(getActivity(),SeemsgActivity.class);
                    //跳转到看消息界面
                    Bundle bundle=new Bundle();
                    bundle.putString("title", data.getTitle());//bundle传值，SeemsgActivity中使用
                    bundle.putString("endtime", data.getEndtime());
                    bundle.putString("content",data.getText());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
    }
}
