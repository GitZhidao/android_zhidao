package com.example.dxnima.zhidao.ui.personcenter.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dxnima.zhidao.R;
import com.example.dxnima.zhidao.bean.table.User;
import com.example.dxnima.zhidao.biz.personcenter.UserPresenter;
import com.example.dxnima.zhidao.ui.personcenter.Activity.SettingActivity;

/**
 * 我的主页界面
 * 对应xml:activity_my
 * Created by DXnima on 2019/4/13.
 */
public class MypageFragment extends Fragment{

    private TextView mypage_setting,mypage_user;

    private ImageView mypage_image;

    private UserPresenter userPresenter;

    private User user;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        userPresenter=new UserPresenter();
        user=new User();
        user=userPresenter.userList.get(0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my, container, false);
        mypage_setting=(TextView) view.findViewById(R.id.mypage_setting);
        mypage_image=(ImageView) view.findViewById(R.id.mypage_image);
        mypage_setting=(TextView) view.findViewById(R.id.mypage_setting);
        mypage_user=(TextView) view.findViewById(R.id.mypage_user);
        mypage_user.setText(user.getUsername());
        mypage_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    //开始
    @Override
    public void onStart() {
        super.onStart();
    }
}