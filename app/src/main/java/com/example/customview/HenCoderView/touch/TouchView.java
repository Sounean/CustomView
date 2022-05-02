package com.example.customview.HenCoderView.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

//点击事件
public class TouchView extends View {
    public TouchView(Context context,AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_UP){
            performClick();
        }
        return false;    // 返回true表示事件不再往下传递
    }
}
