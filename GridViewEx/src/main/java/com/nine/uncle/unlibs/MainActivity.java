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

    private GridView          mGv;
    private List<GeneralBean> mDatas;
    private GvAdapter         mGvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mGv = (GridView) findViewById(R.id.gv);
        mGv.setOnItemClickListener(this);
        mGv.setOnItemLongClickListener(this);
        initData();

    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {//all the data are real data ,
            GeneralBean item = new GeneralBean(i + "", "name " + i, i, null, 0);
            mDatas.add(item);
        }
        mGvAdapter = new GvAdapter(this, mDatas, 4);
        mGv.setAdapter(mGvAdapter);
    }

    /**
     * handle onItemLongClick of the gridview
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     * @return
     */
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        FakeBean item = (FakeBean) parent.getItemAtPosition(position);
        if (item.fakeType == 0) {
            mGvAdapter.remove(position);//remove the item
        }
        return false;
    }

    /**
     * handle onItemClick of the gridview
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FakeBean item = (FakeBean) parent.getItemAtPosition(position);
        if (item.fakeType == 0) {//handle the normal item's click
            GeneralBean bean = (GeneralBean) item;
            Toast.makeText(this, bean.name, Toast.LENGTH_SHORT).show();
        } else if (item.fakeType == 2) {//handle the adding item's click
            GeneralBean bean = new GeneralBean(position + "", "name " + position, position, null, 0);
            mGvAdapter.add(position, bean);
        } else {//handle the empty item's click
            Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();
        }
    }

}
