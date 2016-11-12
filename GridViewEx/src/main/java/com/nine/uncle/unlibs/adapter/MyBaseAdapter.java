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

    /**
     * get the empty data
     *
     * @return
     */
    protected abstract Object getEmptyBean();

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = newView(position, parent);
            holder = getHolder(convertView);//get the holder
            convertView.setTag(holder);//save the holder
        } else {
            holder = (ViewHolder) convertView.getTag();//get the holder
        }

        holder.bindData(position, convertView, parent);//bind the position data to convertView

        return convertView;
    }

    /**
     * add the item data t to the positon ,then update the view
     *
     * @param position
     * @param t
     */
    public void add(int position, T t) {
        mDatas.add(position, t);
        notifyDataSetChanged();
    }

    /**
     * remove the position item ,then update the view
     *
     * @param position
     */
    public void remove(int position) {
        mDatas.remove(position);
        notifyDataSetChanged();
    }

    /**
     * get the newview for convertView,the concrete view implemented by subclass
     *
     * @param position
     * @param parent
     * @return
     */
    public abstract View newView(int position, ViewGroup parent);

    /**
     * get the ViewHolder,the concrete ViewHolder implemented by subclass
     *
     * @param convertView
     * @return
     */
    protected abstract ViewHolder getHolder(View convertView);

    /**
     * ViewHolder for recycling of convertview,and provide the method bindData to bind data to convertView
     */
    public interface ViewHolder {
        /**
         * get the concrete data with the position,then bind the data to convertView
         *
         * @param position
         * @param convertView
         * @param parent
         */
        void bindData(int position, View convertView, ViewGroup parent);
    }
}
