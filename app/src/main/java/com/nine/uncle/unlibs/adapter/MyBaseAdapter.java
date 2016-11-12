package com.nine.uncle.unlibs.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.List;

/**
 * Created by Nine on 2016/11/12.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {
    protected int mNumColums = 3;
    protected Context mContext;
    protected List<T> mDatas;

    public MyBaseAdapter(Context context, List<T> datas, int numcolums) {
        this.mContext = context;
        this.mDatas = datas;
        this.mNumColums = numcolums;
    }

    @Override
    public int getCount() {
        if (mDatas != null && mDatas.size() > 0) {
            int emptyCount = (3 - mDatas.size() % 3) % 3;
            return mDatas.size() + emptyCount;
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(position<mDatas.size()){
            return mDatas.get(position);
        }
        return getFakeBean();
    }

    protected abstract Object getFakeBean();

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = initView(position,convertView,parent);
        return convertView;
    }

    public void add(int position,T t){
        mDatas.add(position,t);
        notifyDataSetChanged();
    }

    public abstract View initView(int position, View convertView, ViewGroup parent);
}
