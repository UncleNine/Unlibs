package com.nine.uncle.unlibs;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nine.uncle.unlibs.adapter.MyBaseAdapter;
import com.nine.uncle.unlibs.bean.FakeBean;
import com.nine.uncle.unlibs.bean.GeneralBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {

    private GridView mGv;
    private List<GeneralBean> mDatas;
    private GvAdapter mGvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mGv = (GridView) findViewById(R.id.gv);
        mDatas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            GeneralBean item = new GeneralBean(i + "", "name " + i, i, null, 0);
            if (i == 9) {
                item.fakeType = 2;
            }
            mDatas.add(item);

        }
        mGvAdapter = new GvAdapter(this, mDatas, 3);

        mGv.setAdapter(mGvAdapter);
        mGv.setOnItemClickListener(this);
        mGv.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        FakeBean item = (FakeBean) parent.getItemAtPosition(position);
        if (item.fakeType == 0) {
            mGvAdapter.remove(position);
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FakeBean item = (FakeBean) parent.getItemAtPosition(position);
        if (item.fakeType == 0) {
            GeneralBean bean = (GeneralBean) item;
            Toast.makeText(this, bean.name, Toast.LENGTH_SHORT).show();
        } else if (item.fakeType == 2) {
            GeneralBean bean = new GeneralBean(position + "", "name " + position, position, null, 0);
            mGvAdapter.add(position, bean);
        } else {
            Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();
        }
    }


    class GvAdapter extends MyBaseAdapter<GeneralBean> {

        public GvAdapter(Context context, List<GeneralBean> datas, int numcolums) {
            super(context, datas, numcolums);
        }

        @Override
        protected Object getFakeBean() {
            return new FakeBean(1);
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


}
