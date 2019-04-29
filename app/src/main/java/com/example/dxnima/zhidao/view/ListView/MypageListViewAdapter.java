package com.example.dxnima.zhidao.view.ListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dxnima.zhidao.R;

import java.util.LinkedList;

/**
 * 关注的listview的适配器
 * Created by DXnima on 2019/4/29.
 */

public class MypageListViewAdapter extends BaseAdapter {

    private Context mContext;
    private LinkedList<MypageListViewData> mData;

    public MypageListViewAdapter(LinkedList<MypageListViewData> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.common_mypage_itemlist, parent, false);
            holder = new ViewHolder();
            holder.list_msgtitel = (TextView) convertView.findViewById(R.id.tv_theme_title);
            holder.list_text=(TextView) convertView.findViewById(R.id.tv_theme_content);
            holder.list_msgendtime=(TextView) convertView.findViewById(R.id.tv_dead_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.list_msgtitel.setText(mData.get(position).getTitle());
        holder.list_text.setText(mData.get(position).getText());
        holder.list_msgendtime.setText(mData.get(position).getEndtime());
        return convertView;
    }

    //添加一个元素
    public void add(MypageListViewData data) {
        if (mData == null) {
            mData = new LinkedList<>();
        }
        mData.add(data);
        notifyDataSetChanged();
    }

    //往特定位置，添加一个元素
    public void add(int position,MypageListViewData data){
        if (mData == null) {
            mData = new LinkedList<>();
        }
        mData.add(position, data);
        notifyDataSetChanged();
    }

    public void remove(MypageListViewData data) {
        if(mData != null) {
            mData.remove(data);
        }
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if(mData != null) {
            mData.remove(position);
        }
        notifyDataSetChanged();
    }

    public void clear() {
        if(mData != null) {
            mData.clear();
        }
        notifyDataSetChanged();
    }


    private class ViewHolder {
        TextView list_msgtitel,list_msgendtime,list_text;
    }

}
