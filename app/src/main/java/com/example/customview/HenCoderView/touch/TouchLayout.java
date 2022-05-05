package com.example.customview.HenCoderView.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

/*
* viewgroup的触摸反馈基础
* */
public class TouchLayout extends ViewGroup {
    public TouchLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {  //每次move都会调用这个
        int delta = (int) ev.getY(); // 判断纵向的滑动距离是不是超过了某个值
        int SLOP = 10;
        // 是不是大于某个值，如果大于则返回true(模拟了滑动太长了，应该是想要滑动（父view），而不是想去点击(子view))
        if (Math.abs(delta)>SLOP){
            return true;    //一旦返回了true，便交给自己的onTouchEvent()处理事件，也不会再调用onInterceptTouchEvent()了
        }else {
            return false;
        }
    }
}
