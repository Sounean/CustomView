package com.example.customview.HenCoderView.conflict.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * @Author : Sounean
 * @Time : On 2022-05-04 15:50
 * @Description : CustomTextView
 * @Warn :
 */

public class CustomTextView extends androidx.appcompat.widget.AppCompatTextView {
    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("------->", "dispatchTouchEvent:down????? ");
                break;
            case MotionEvent.ACTION_MOVE:
                getParent().requestDisallowInterceptTouchEvent(true);   // 如果是textview被滑动了就拦截这个事件
                Log.e("------->", "dispatchTouchEvent:move????? ");
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:break;
        }
        return super.dispatchTouchEvent(event);
    }
}
