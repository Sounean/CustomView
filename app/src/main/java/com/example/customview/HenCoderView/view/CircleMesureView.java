package com.example.customview.HenCoderView.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.customview.HenCoderView.Util;

/*
* 简单改写已有的view尺寸 根据里面的圆，去测量view的宽高
* */
public class CircleMesureView extends View {
    private static final int RADIUS = (int) Util.dp2px(80);
    private static final int PADDING = (int) Util.dp2px(30);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public CircleMesureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = (PADDING+RADIUS)*2;
        int height = (PADDING+RADIUS)*2;
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.RED);    // view背景为红色
        canvas.drawCircle(PADDING+RADIUS,PADDING+RADIUS,RADIUS,paint);
    }

}
