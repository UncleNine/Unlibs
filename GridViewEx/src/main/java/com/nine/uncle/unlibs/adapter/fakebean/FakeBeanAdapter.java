package com.nine.uncle.unlibs.adapter.fakebean;

import android.content.Context;

import com.nine.uncle.unlibs.adapter.MyBaseAdapter;
import com.nine.uncle.unlibs.bean.FakeBean;

import java.util.List;

/**
 * Created by Nine on 2016/11/12.
 */
public abstract class FakeBeanAdapter<T> extends MyBaseAdapter {

    private int mCount;

    public FakeBeanAdapter(Context context, List<T> datas, int numcolums) {
        super(context, datas, numcolums);
        mCount = numcolums;
    }


    /**
     * get the count for post binddata
     *
     * @return
     */
    @Override
    public int getCount() {
        if (mDatas != null) {
            mCount = mNumColums - mDatas.size() % mNumColums;
            return mDatas.size() + mCount;
        }
        return mCount;
    }

    /**
     * get the item through the position,if the position is not in mData,then generate a fake item
     *
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        if (mDatas != null) {
            if (position < mDatas.size()) {
                return mDatas.get(position);
            } else if (position == mDatas.size()) {
                return getAddFakeBean();
            } else {
                return getEmptyFakeBean();
            }
        } else if (position == 0) {
            return getAddFakeBean();
        }
        return getEmptyBean();
    }

    @Override
    public Object getEmptyBean() {
        return getEmptyFakeBean();
    }

    public Object getEmptyFakeBean() {
        return new FakeBean(1);
    }

    public Object getAddFakeBean() {
        return new FakeBean(2);
    }
}
