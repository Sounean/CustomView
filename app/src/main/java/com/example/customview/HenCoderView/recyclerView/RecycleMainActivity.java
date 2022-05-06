package com.example.customview.HenCoderView.recyclerView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.customview.HenCoderView.recyclerView.AddItemDecoration.AddItemDecorationRecycleAct;
import com.example.customview.HenCoderView.recyclerView.LayoutManager.LayoutManagerRecycleAct;
import com.example.customview.HenCoderView.recyclerView.Simple.SimpleRecycleAct;
import com.example.customview.R;

/*
* 学习顺序为1.AddItemDecorationRecycleAct 简单的recycleview的构成
* */
public class RecycleMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_main);
    }

    public void btn_jumpSimpleRecycle(View view) {
        Intent intent = new Intent();
        intent.setClass(RecycleMainActivity.this, SimpleRecycleAct.class);
        startActivity(intent);
    }

    public void btn_jumpLayoutManager(View view) {
        Intent intent = new Intent();
        intent.setClass(RecycleMainActivity.this, LayoutManagerRecycleAct.class);
        startActivity(intent);
    }

    public void btn_jumpItemDecoration(View view) {
        Intent intent = new Intent();
        intent.setClass(RecycleMainActivity.this, AddItemDecorationRecycleAct.class);
        startActivity(intent);
    }
}