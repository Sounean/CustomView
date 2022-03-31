package com.example.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/*
* 0.简单了解zidingyiview
* */
public class CustomView extends View {
    private static final String TAG = CustomView.class.getSimpleName();

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TestDemo);

        String text = ta.getString(R.styleable.TestDemo_text);
        int textAttr = ta.getInteger(R.styleable.TestDemo_testAttr, -1);

        Log.e(TAG, "text = " + text + " , textAttr = " + textAttr);

        ta.recycle();
    }
}
