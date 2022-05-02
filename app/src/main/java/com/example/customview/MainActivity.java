package com.example.customview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.example.customview.HenCoderView.Util;
import com.example.customview.HenCoderView.view.CameraVIew;
import com.example.customview.HenCoderView.view.CircleVIew;

public class MainActivity extends AppCompatActivity  {
    private static String TAG = "------->";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout c1 = findViewById(R.id.c1);// 父view
        FrameLayout f1 = findViewById(R.id.f1);// 位子在view下面的
        View view = findViewById(R.id.view);


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
}