package com.example.dxnima.zhidao.ui.personcenter.Fragment;

import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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
import android.widget.Toast;

import com.example.dxnima.zhidao.R;
import com.example.dxnima.zhidao.bean.table.User;
import com.example.dxnima.zhidao.biz.personcenter.InterfaceView.IMsgView;
import com.example.dxnima.zhidao.biz.personcenter.UserPresenter;
import com.example.dxnima.zhidao.bridge.BridgeFactory;
import com.example.dxnima.zhidao.bridge.Bridges;
import com.example.dxnima.zhidao.bridge.cache.sharePref.EBSharedPrefManager;
import com.example.dxnima.zhidao.bridge.cache.sharePref.EBSharedPrefUser;
import com.example.dxnima.zhidao.ui.personcenter.Activity.SendmsgActivity;
import com.example.dxnima.zhidao.ui.personcenter.Activity.SettingActivity;
import com.example.dxnima.zhidao.util.ToastUtil;
import com.example.dxnima.zhidao.view.MyCircleImageView;

import java.util.ArrayList;

/**
 * 我的主页界面
 * 对应xml:activity_my
 * Created by DXnima on 2019/4/13.
 */
public class MypageFragment extends Fragment implements View.OnClickListener{

    private TextView mypage_setting,mypage_user;

    private MyCircleImageView mypage_image;

    private EBSharedPrefManager ebSharedPrefManager;
    private String username;
    private TabLayout tabmine;
    private ViewPager vpmine;
    private String[] mTabTitles = new String[]
            {"我的","收藏"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ebSharedPrefManager = BridgeFactory.getBridge(Bridges.SHARED_PREFERENCE);
        username=ebSharedPrefManager.getKDPreferenceUserInfo().getString
                (EBSharedPrefUser.USER_NAME,"用户名为空！");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mypage_setting=(TextView) view.findViewById(R.id.mypage_setting);
        mypage_image=(MyCircleImageView) view.findViewById(R.id.mypage_image);
        mypage_user=(TextView) view.findViewById(R.id.mypage_user);
        mypage_user.setText(username);
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
        initListeners();

    }

    public void initListeners(){
        mypage_setting.setOnClickListener(this);
        mypage_image.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mypage_setting:
                Intent intent = new Intent(getActivity().getApplicationContext(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.mypage_image:
                showSheetDialog();
                break;
        }
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

    public void showSheetDialog(){
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.pop_mine_avatar_sheet, null);
        TextView tvcapture=(TextView) view.findViewById(R.id.popmine_tv_capture);
        TextView tvalarm=(TextView) view.findViewById(R.id.popmine_tv_alarm);
        TextView tvcancel=(TextView) view.findViewById(R.id.popmine_tv_cancel);
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setView(view);
        final AlertDialog dialog=builder.create();
        dialog.show();
        tvcapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.makeText(getActivity(),"打开相机");
            }
        });
        tvalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.makeText(getActivity(),"打开相册");
            }
        });
        tvcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        //让弹窗底部显示
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager m = getActivity().getWindowManager();
        Display d = m.getDefaultDisplay(); //为获取屏幕宽、高
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); //获取对话框当前的参数值
        p.width = d.getWidth(); //宽度设置为屏幕
        dialog.getWindow().setAttributes(p); //设置生效
       }
}