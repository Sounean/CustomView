package com.example.customview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.airbnb.lottie.Lottie;
import com.example.customview.HenCoderView.Lottie.SplashScreen;
import com.example.customview.HenCoderView.ScrollListView.ScrollListAct;
import com.example.customview.HenCoderView.conflict.ConflictMainAct;
import com.example.customview.HenCoderView.recyclerView.RecycleMainActivity;
import com.example.customview.HenCoderView.rotate.RotateAct;

public class MainActivity extends AppCompatActivity  {
    private static String TAG = "------->";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout c1 = findViewById(R.id.c1);// 父view
       // FrameLayout f1 = findViewById(R.id.f1);// 位子在view下面的
        //View view = findViewById(R.id.view);


    }

    public void c1Click(View view) {
        Log.e(TAG, "c1Click ");
    }

    public void f1Click(View view) {
        Log.e(TAG, "f1Click  ");
    }

    public void viewClick(View view) {
        Log.e(TAG, "viewClick ");
    }

    public void jump_conflict(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, ConflictMainAct.class);
        startActivity(intent);
    }

    public void jump_scrollview(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, ScrollListAct.class);
        startActivity(intent);
    }

    public void jump_recycleview(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, RecycleMainActivity.class);
        startActivity(intent);
    }

    public void jump_rotate(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, RotateAct.class);
        startActivity(intent);
    }

    public void jump_lottie(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, SplashScreen.class);
        startActivity(intent);
    }
}