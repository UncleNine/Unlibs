package com.nine.uncle.unlibs.adapter.fakebean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nine.uncle.unlibs.R;
import com.nine.uncle.unlibs.adapter.MyBaseAdapter;
import com.nine.uncle.unlibs.bean.FakeBean;
import com.nine.uncle.unlibs.bean.GeneralBean;

import java.util.List;

/**
 * Created by Nine on 2016/11/13.
 */
public class GvAdapter extends FakeBeanAdapter<GeneralBean> {

    public GvAdapter(Context context, List<GeneralBean> datas, int numcolums) {
        super(context, datas, numcolums);
    }

    @Override
    protected MyBaseAdapter.ViewHolder getHolder(View convertView) {
        ViewHolder holder = new ViewHolder();
        holder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
        holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);

        return holder;
    }

    @Override
    public View newView(int position, ViewGroup parent) {
        return View.inflate(mContext, R.layout.item_gv_tviv, null);
    }


    class ViewHolder implements MyBaseAdapter.ViewHolder{
        TextView tvName;
        ImageView ivIcon;

        @Override
        public void bindData(int position, View convertView, ViewGroup parent) {
            FakeBean item = (FakeBean) getItem(position);
            this.ivIcon.setVisibility(View.VISIBLE);
            convertView.setBackgroundResource(android.R.color.darker_gray);
            if (item.fakeType == 1) {
                this.tvName.setText("");
                this.ivIcon.setVisibility(View.INVISIBLE);
                convertView.setBackgroundResource(android.R.color.white);
            } else if (item.fakeType == 2) {
                this.ivIcon.setImageResource(R.mipmap.ic_menu_add);
                this.tvName.setText("");
            } else {
                GeneralBean bean = (GeneralBean) item;
                this.ivIcon.setImageResource(R.mipmap.ic_launcher);
                this.tvName.setText(bean.name);
            }
        }
    }
}