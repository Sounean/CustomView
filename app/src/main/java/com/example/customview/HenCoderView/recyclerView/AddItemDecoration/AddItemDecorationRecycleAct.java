package com.example.customview.HenCoderView.recyclerView.AddItemDecoration;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customview.HenCoderView.recyclerView.Simple.SimpleRecycleAdapter;
import com.example.customview.R;

import java.util.ArrayList;

/*
* 本身是直接复制了SimpleRecycleAct的，所以多出的步骤都从3开始
* 这个类主要讲解给item添加修饰.
* 如果只是增加线，直接addItemDecoration给recycleview就行，如果要更多效果，就需要自定义
* */
public class AddItemDecorationRecycleAct extends AppCompatActivity {
    private ArrayList<String> mDatas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_recycle);

        initSimpleData();    // 初始化填充recycleview的数据
        initSimpleRecycleView();    // 初始化recycleview
    }

    private void initSimpleRecycleView() {
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.rv_simple_view);

        //1.普通线性布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //2.瀑布流效果 第二个参数如果是横则表示几行，如果是列则表示几列
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
//        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        mRecyclerView.setLayoutManager(gridLayoutManager);

        // 3.初始化分割线、添加分割线（原生）
//        DividerItemDecoration mDivider = new DividerItemDecoration(this,
//                DividerItemDecoration.VERTICAL);
//        mRecyclerView.addItemDecoration(mDivider);
        // 3.初始化分割线、添加分割线（自定义方式）
        mRecyclerView.addItemDecoration(new LinearItemDecoration());

        SimpleRecycleAdapter adapter = new SimpleRecycleAdapter(this, mDatas);
        mRecyclerView.setAdapter(adapter);
    }

    private void initSimpleData() {
        for (int i = 0; i < 200; i++) {
            mDatas.add("第 " + i + " 个item");
        }
    }
}