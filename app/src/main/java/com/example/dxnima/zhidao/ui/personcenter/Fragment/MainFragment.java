package com.example.dxnima.zhidao.ui.personcenter.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.dxnima.zhidao.R;
import com.example.dxnima.zhidao.bean.table.Msg;
import com.example.dxnima.zhidao.biz.personcenter.MsgPresenter;
import com.example.dxnima.zhidao.ui.personcenter.Activity.AllmsgActivity;
import com.example.dxnima.zhidao.view.MyAdapter;
import com.example.dxnima.zhidao.view.MyListViewData;

import java.util.LinkedList;
import java.util.List;

/**
 * 消息通知页面
 * 对应xml:activity_main
 * Created by DXnima on 2019/4/13.
 */
public class MainFragment extends Fragment {

    private SearchView searchView;
    private ListView listMsg;
    private TextView txt_empty;
    private MyAdapter mAdapter = null;
    private List<MyListViewData> mData = null;
    private List<Msg> msgList=null;
    private Msg msg;
    private MsgPresenter msgPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //listview初始化
        mData = new LinkedList<MyListViewData>();
        mAdapter = new MyAdapter((LinkedList<MyListViewData>) mData,getActivity());
    }

    //创造View
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        searchView=(SearchView) view.findViewById(R.id.searchView);
        listMsg=(ListView) view.findViewById(R.id.listSubject);
        txt_empty=(TextView) view.findViewById(R.id.text_empty);
        initData();
        bindViews();
        return view;
    }

    //开始
    @Override
    public void onStart() {
        for (int position = 0; position <mData.size(); position++) {
            //item的点击事件，里面可以设置跳转并传值
            listMsg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), AllmsgActivity.class);//跳转到消息界面
                    startActivity(intent);
                }
            });
        }
        super.onStart();
    }

    private void bindViews() {
        listMsg.setAdapter(mAdapter);
        txt_empty.setText("暂无主题~");

        listMsg.setEmptyView(txt_empty);
    }

    //修改某一行的list数据
    private void updateListItem(int postion,MyListViewData mData){
        int visiblePosition = listMsg.getFirstVisiblePosition();
        View v = listMsg.getChildAt(postion - visiblePosition);
        ImageView msgimage = (ImageView) v.findViewById(R.id.list_msgimage);
        TextView msgtitel = (TextView) v.findViewById(R.id.list_msgtitle);
        TextView msgendtime=(TextView) v.findViewById(R.id.list_msgendtime);
        msgimage.setImageResource(mData.getImgId());
        msgtitel.setText(mData.getTitle());
        msgendtime.setText(mData.getEndtime());
    }

    //添加list数据
    public void initData() {
//        msgList=msgPresenter.msgList;
//        if (msgList==null) return;
//        else
//        for (int i = 0; i < msgList.size(); i++) {
//            msg = msgList.get(i);
//            mAdapter.add(new MyListViewData(R.drawable.xingxing, msg.getTitle(), msg.getEndtime()));
//        }
        mAdapter.add(new MyListViewData(R.drawable.xingxing,"考试","类别：","学习"));
    }
}