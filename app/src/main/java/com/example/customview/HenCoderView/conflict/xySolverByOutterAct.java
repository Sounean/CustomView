package com.example.customview.HenCoderView.conflict;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.customview.HenCoderView.conflict.view.CustomTextView;
import com.example.customview.R;


/*
 * 外部处理就是在父view中就给拦截了。（主要是通过onInterceptTouchEvent里返回值来判断时间要不要给自己的onTouch处理）
 * */
public class xySolverByOutterAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xy_solvebyoutter);

//        TextView customTextView = findViewById(R.id.tv);
//        customTextView.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}