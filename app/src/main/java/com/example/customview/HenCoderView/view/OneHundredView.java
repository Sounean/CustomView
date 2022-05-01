package com.example.customview.HenCoderView.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.customview.HenCoderView.Util;

/**
 * @Author : Sounean
 * @Time : On 2022-05-01 23:36
 * @Description : OneHundredView
 * @Warn :
 */
public class OneHundredView extends View {
    public OneHundredView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 这个方法意思是通知测量完毕，大小是100*100
        setMeasuredDimension((int) Util.dp2px(100),(int)Util.dp2px(100));
    }


}
