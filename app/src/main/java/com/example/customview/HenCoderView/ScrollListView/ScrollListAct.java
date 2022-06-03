package com.example.customview.HenCoderView.ScrollListView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.customview.HenCoderView.ScrollListView.adapter.DelScrollListViewAdapter;
import com.example.customview.HenCoderView.ScrollListView.view.DelScrollListView;
import com.example.customview.HenCoderView.ScrollListView.view.ScrollerLinearLayout;
import com.example.customview.HenCoderView.ScrollListView.view.itemScrollLinearLayout;
import com.example.customview.R;

import java.util.ArrayList;
import java.util.List;

public class ScrollListAct extends AppCompatActivity implements View.OnClickListener,itemScrollLinearLayout.OnScrollListener {

    private static final int DISTANCE = 300;
    private ScrollerLinearLayout mScrollerLinearLayout;
    private DelScrollListView mDelScrollListView;
    private DelScrollListViewAdapter mDelScrollListViewAdapter;
    private itemScrollLinearLayout mLastScrollView; // 记录上一个被拉出来的item

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_util);

        initScrollLinearLayout();   // 向左滑动
        initDelScrollListView();   // 可滑动删除的listview

    }

    /*
    * 向左滑动
    * */
    private void initDelScrollListView() {
        mDelScrollListView = findViewById(R.id.delScrollListView_root);
        final List<DelScrollListViewAdapter.DataHolder> items = new ArrayList<DelScrollListViewAdapter.DataHolder>();
        for(int i=0;i<20;i++){
            DelScrollListViewAdapter.DataHolder item = new DelScrollListViewAdapter.DataHolder();
            item.title = "第"+i+"项";
            items.add(item);
        }
        mDelScrollListViewAdapter = new DelScrollListViewAdapter(this,items,this, (itemScrollLinearLayout.OnScrollListener) this);
        mDelScrollListView.setAdapter(mDelScrollListViewAdapter);
    }

    /*
    * 滑动复位listview
    * */
    private void initScrollLinearLayout() {
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_reset).setOnClickListener(this);
        mScrollerLinearLayout = findViewById(R.id.scroller_root);
    }
    // 监听itemScrollLinearLayout中的ACTION_UP时关于是否开始拉出来下一个item时，缩回上一个
    @Override
    public void OnScroll(itemScrollLinearLayout view) {
        if (mLastScrollView != null){
            mLastScrollView.smoothScrollTo(0,0);
        }
        mLastScrollView = view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_start:
                mScrollerLinearLayout.startScroll(0,DISTANCE);
                break;
            case R.id.btn_reset:
                mScrollerLinearLayout.startScroll(DISTANCE,-DISTANCE);
                break;
            case R.id.del:
                int positionForView = mDelScrollListView.getPositionForView(v);
                mDelScrollListViewAdapter.removeItem(positionForView);

        }
    }
}