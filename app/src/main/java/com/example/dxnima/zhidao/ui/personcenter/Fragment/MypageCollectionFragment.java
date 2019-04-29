package com.example.dxnima.zhidao.ui.personcenter.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dxnima.zhidao.R;

/**
 * 收藏
 * Created by DXnima on 2019/4/29.
 */

public class MypageCollectionFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mypage_collection, container, false);
        return view;
    }
}
