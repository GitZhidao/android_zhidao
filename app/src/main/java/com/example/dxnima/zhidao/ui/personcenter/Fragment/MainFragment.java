package com.example.dxnima.zhidao.ui.personcenter.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.dxnima.zhidao.R;
import com.example.dxnima.zhidao.bean.table.Subject;
import com.example.dxnima.zhidao.biz.personcenter.GetSubjectPresenter;
import com.example.dxnima.zhidao.biz.personcenter.InterfaceView.ISubjectView;
import com.example.dxnima.zhidao.biz.personcenter.SubjectPresenter;
import com.example.dxnima.zhidao.ui.personcenter.Activity.AllmsgActivity;
import com.example.dxnima.zhidao.view.MyAdapter;
import com.example.dxnima.zhidao.view.MyListViewData;

import java.util.LinkedList;
import java.util.List;
/**
 * 主题页面
 * 对应xml:activity_main
 * Created by DXnima on 2019/4/13.
 */
public class MainFragment extends Fragment implements ISubjectView{

    private SearchView searchView;
    private ListView listMsg;
    private TextView txt_empty;
    private MyAdapter mAdapter = null;
    private List<MyListViewData> mData = null;
    private List<Subject> subjectList=null;
    private SubjectPresenter subjectPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //listview初始化
        subjectPresenter=new SubjectPresenter();
        subjectPresenter.attachView(this);
        subjectPresenter.allSendSubject();
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
        bindViews();
        return view;
    }

    //开始
    @Override
    public void onStart() {
        super.onStart();
    }


    private void bindViews() {
        listMsg.setAdapter(mAdapter);
        txt_empty.setText("暂无主题~");
        listMsg.setEmptyView(txt_empty);
    }

    //进入主界面请求得到以下数据
    public void allRequst(){
        SubjectPresenter subjectPresenter=new SubjectPresenter();
        GetSubjectPresenter getSubjectPresenter=new GetSubjectPresenter();
        subjectPresenter.allSendSubject();//所有发布的主题
        getSubjectPresenter.allFocusSubject();//所有关注的主题
    }

    @Override
    public void onError(String errorMsg, String code) {

    }

    @Override
    public void onSuccess() {
        initData();
        for (int position = 0; position <mData.size(); position++) {
            //item的点击事件，里面可以设置跳转并传值
            listMsg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), AllmsgActivity.class);//跳转到消息界面
                    Bundle bundle=new Bundle();
                    bundle.putString("code",subjectList.get(position).getCode());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    //添加list数据
    public void initData() {
        subjectList=subjectPresenter.subjectList;
        Subject subject;
        if (subjectList==null) return;
        else
            for (int i = 0; i < subjectList.size(); i++) {
                subject = subjectList.get(i);
                mAdapter.add(new MyListViewData(R.drawable.xingxing1, subject.getSubtitle(),"编号：",subject.getCode()));
            }
    }
}