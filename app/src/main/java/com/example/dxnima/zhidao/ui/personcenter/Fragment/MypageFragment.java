package com.example.dxnima.zhidao.ui.personcenter.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dxnima.zhidao.R;
import com.example.dxnima.zhidao.bean.table.User;
import com.example.dxnima.zhidao.biz.personcenter.UserPresenter;
import com.example.dxnima.zhidao.ui.personcenter.Activity.SettingActivity;

import java.util.ArrayList;

/**
 * 我的主页界面
 * 对应xml:activity_my
 * Created by DXnima on 2019/4/13.
 */
public class MypageFragment extends Fragment{

    private TextView mypage_setting,mypage_user;

    private ImageView mypage_image;

    private UserPresenter userPresenter;
    private TabLayout tabmine;
    private ViewPager vpmine;
    private User user;
    private String[] mTabTitles = new String[]
            {"我的","收藏"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        userPresenter=new UserPresenter();
        user=new User();
        if (userPresenter.userList!=null)
            user=userPresenter.userList.get(0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
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
        tabmine=(TabLayout) view.findViewById(R.id.tab_mine);
        vpmine=(ViewPager) view.findViewById(R.id.vp_mine);
        //设置适配器  注意：getChildFragmentManager
        vpmine.setAdapter(new MyAdapter(getChildFragmentManager()));
        //建立关联
        tabmine.setupWithViewPager(vpmine);
        //一次加载所有的页面
        vpmine.setOffscreenPageLimit(mTabTitles.length);
    }

    //开始
    @Override
    public void onStart() {
        super.onStart();
    }

    //    写一个适配器
    class MyAdapter extends FragmentPagerAdapter {

        //得到页面的title,会添加到tabLayout控件上
        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles[position];
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            android.support.v4.app.Fragment f=null;
//            进行判断
            switch (position){
                case 0:
                    f=new MypageSubjectFragment();
                    break;
                case 1:
                    f=new MypageCollectionFragment();
                    break;
            }
            return f;
        }

        //view的页数
        @Override
        public int getCount() {
            return mTabTitles.length;
        }

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }
    }
}