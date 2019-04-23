package com.example.dxnima.zhidao.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dxnima.zhidao.R;

import java.util.LinkedList;

/**
 *listView适配器
 * Created by Jay on 2015/9/21 0021.
 */
public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private LinkedList<MyListViewData> mData;

    public MyAdapter(LinkedList<MyListViewData> mData,Context mContext) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.common_itemlist, parent, false);
            holder = new ViewHolder();
            holder.list_msgimage = (ImageView) convertView.findViewById(R.id.list_msgimage);
            holder.list_msgtitel = (TextView) convertView.findViewById(R.id.list_msgtitle);
            holder.list_msgendtime=(TextView) convertView.findViewById(R.id.list_msgendtime);
            holder.list_texttime=(TextView) convertView.findViewById(R.id.list_texttime);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.list_msgimage.setImageResource(mData.get(position).getImgId());
        holder.list_msgtitel.setText(mData.get(position).getTitle());
        holder.list_msgendtime.setText(mData.get(position).getEndtime());
        holder.list_texttime.setText(mData.get(position).getText());
        return convertView;
    }

    //添加一个元素
    public void add(MyListViewData data) {
        if (mData == null) {
            mData = new LinkedList<>();
        }
        mData.add(data);
        notifyDataSetChanged();
    }

    //往特定位置，添加一个元素
    public void add(int position,MyListViewData data){
        if (mData == null) {
            mData = new LinkedList<>();
        }
        mData.add(position, data);
        notifyDataSetChanged();
    }

    public void remove(MyListViewData data) {
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
        ImageView list_msgimage;
        TextView list_msgtitel,list_msgendtime,list_texttime;
    }

}
