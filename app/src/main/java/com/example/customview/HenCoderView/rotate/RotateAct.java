package com.example.customview.HenCoderView.rotate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.customview.HenCoderView.rotate.githubCode.ChargingRotateView;
import com.example.customview.HenCoderView.rotate.githubCode.ClockVIew;
import com.example.customview.R;

public class RotateAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate);

//        ClockVIew clockVIew = findViewById(R.id.clock_img_view);
//        clockVIew.performAnimation();

        AppCompatImageView view = findViewById(R.id.iv_charge);
        int[] viewLocation = new int[2];
        view.getLocationInWindow(viewLocation);
        int viewX = viewLocation[0]; // x 坐标
        int viewY = viewLocation[1]; // y 坐标
        //view.performAnimation();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "rotation",  0, 360);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setDuration(3000);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();
    }
}