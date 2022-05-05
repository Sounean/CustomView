package com.example.customview.HenCoderView.conflict;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customview.HenCoderView.conflict.view.CustomTextView;
import com.example.customview.R;

/*
* 内部处理就是在子view中就给处理了。（主要是通过dispatchTouchEvent里调用getParent().requestDisallowInterceptTouchEvent(true);）
* */
public class xySolverByInnerAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xy_solvebyinner);

        CustomTextView customTextView = findViewById(R.id.tv);
        customTextView.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}