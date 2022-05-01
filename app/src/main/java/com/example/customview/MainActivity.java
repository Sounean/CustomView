package com.example.customview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.customview.HenCoderView.Util;
import com.example.customview.HenCoderView.view.CameraVIew;
import com.example.customview.HenCoderView.view.CircleVIew;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CameraVIew view = findViewById(R.id.view);

        /*
        * （变换的view对象，做出动画动作的属性（用来获取初始值），做出动画动作的最终值）
        * */
//        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"radius",
//                Util.dp2px(150));//创建一个float类型的动画
//        animator.setStartDelay(1000);
//        animator.start();

        //        view.animate()
//                .translationX(Util.dp2px(200))  // 向右边平移200
//                .translationY(100)
//                .rotation(180)  // 旋转180度
//                .setStartDelay(1000)    // 缓冲总共1000ms（不然一下子就过去了）
//                .start();
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