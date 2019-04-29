package com.example.dxnima.zhidao.ui.personcenter.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.dxnima.zhidao.R;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData = new LinkedList<MypageListViewData>();
        mAdapter = new MypageListViewAdapter((LinkedList<MypageListViewData>) mData,getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mypage_suject, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        sujectListView=(ListView) view.findViewById(R.id.mypage_suject_listView);
        initData();
        bindViews();
    }

    private void bindViews() {
        sujectListView.setAdapter(mAdapter);
    }

    private void initData(){
        //TODO 我的加数据
        mAdapter.add(new MypageListViewData("英语考试","尼玛12346是顶顶顶顶顶顶顶顶顶顶顶顶",
                "结束时间：2019-10-02 10：25：10"));
        mAdapter.add(new MypageListViewData("英语考试","王尼玛12346是顶顶顶顶顶顶顶顶顶顶顶顶",
                "结束时间：2019-10-02 10：25：10"));
        mAdapter.add(new MypageListViewData("英语考试","王尼玛12346是顶顶顶顶顶顶顶顶顶顶顶顶滴滴答答3",
                "结束时间：2019-10-02 10：25：10"));
        mAdapter.add(new MypageListViewData("英语考试","王尼玛12346是顶顶顶顶顶顶顶顶顶顶顶顶哈哈哈哈哈哈哈哈哈",
                "结束时间：2019-10-02 10：25：10"));
        mAdapter.add(new MypageListViewData("英语考试","android好难做啊啊啊啊啊啊啊啊啊啊啊啊哈哈哈哈哈哈哈哈哈王尼玛12346是顶顶顶顶顶顶顶顶顶顶顶顶哈哈哈哈哈哈哈哈哈",
                "结束时间：2019-10-02 10：25：10"));
    }

}
