package com.example.dxnima.zhidao.ui.personcenter.Activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dxnima.zhidao.R;
import com.example.dxnima.zhidao.biz.personcenter.InterfaceView.IMsgView;
import com.example.dxnima.zhidao.biz.personcenter.MsgPresenter;
import com.example.dxnima.zhidao.ui.base.BaseActivity;
import com.example.dxnima.zhidao.util.ToastUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 主题发送页面
 * 对应xml:activity_addmsg
 * Created by DXnima on 2019/4/11.
 */
public class SendmsgActivity extends BaseActivity implements IMsgView{

    private MsgPresenter mMsgPresenter;
    private TagFlowLayout gltags;
    private TextView addlabel;
    public List<String> mVals1=new ArrayList<>();
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
        addlabel=(TextView) findViewById(R.id.addmsg_tv_add_label);
        mVals1.add("王尼玛");
        addTag(mVals1);
    }

    // 初始化公共头部
    @Override
    public void setHeader() {
        super.setHeader();
        title.setText("发送通知");
    }

    @Override
    public void initListeners() {
        addlabel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addmsg_tv_add_label:
                showAddlabelDialog();
                break;
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

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    //添加标签
    public void addTag(final List<String> mVals){

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
                Toast.makeText(SendmsgActivity.this, mVals.get(position), Toast.LENGTH_SHORT)
                        .show();
                //view.setVisibility(View.GONE);
                return true;
            }
        });
    }

    //添加标签对话框
    public void showAddlabelDialog(){
        LayoutInflater inflater = LayoutInflater.from(getApplication());
        View view = inflater.inflate(R.layout.pop_add_label, null);
        final EditText etlabel=(EditText) view.findViewById(R.id.addtag_et_label);
        Button btncancel=(Button) view.findViewById(R.id.addtag_btn_cancel_pop);
        Button btnsubmit=(Button) view.findViewById(R.id.addtag_btn_submit_pop);
        AlertDialog.Builder builder=new AlertDialog.Builder(SendmsgActivity.this);
        builder.setView(view);
        final AlertDialog dialog=builder.create();
        dialog.show();
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭对话框
                dialog.dismiss();
            }
        });
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etlabel.getText().toString().equals("")){
                    ToastUtil.makeText(SendmsgActivity.this,"不能为空！");
                }
                else {
                    dialog.dismiss();
                    mVals1.add(etlabel.getText().toString());
                    addTag(mVals1);
                    ToastUtil.makeText(SendmsgActivity.this,"标签添加成功！");
                }
            }
        });
    }
}