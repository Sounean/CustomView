package com.example.customview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*CustomTitleView title = findViewById(R.id.titelDemo);
       // title.setOnClickListener(this);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("--------------------->", "外面给他新建的点击事件12 " );
            }
        });*/


        /*TimerBottom timerBtn = findViewById(R.id.timer_btn_demo);
        timerBtn.startCountDown();*/
    }


    /*@Override
    public void onClick(View view) {
        Log.e("--------------------->", "外面给他新建的点击事件1 " );
    }*/
}