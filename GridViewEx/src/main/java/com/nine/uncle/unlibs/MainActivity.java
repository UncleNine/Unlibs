package com.nine.uncle.unlibs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.nine.uncle.unlibs.adapter.fakebean.GvAdapter;
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
        for (int i = 0; i < 10; i++) {//all the data are real data ,
            GeneralBean item = new GeneralBean(i + "", "name " + i, i, null, 0);
            mDatas.add(item);
        }
        //mGv.setNumColumns(4);
        mGvAdapter = new GvAdapter(this, mDatas, 4);

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

}
