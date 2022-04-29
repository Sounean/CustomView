package com.example.customview.HenCoderView.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.customview.HenCoderView.Util;

public class SportsView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final int RADIUS = (int) Util.dp2px(150);
    private static final int RING_WIDTH = (int) Util.dp2px(3);

    public SportsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制环
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#2979FF"));
        paint.setStrokeWidth(RING_WIDTH);
        canvas.drawCircle(getWidth()/2,getHeight()/2,RADIUS,paint);

        //绘制进度条
        paint.setColor(Color.parseColor("#FFFFFF"));
        paint.setStrokeCap(Paint.Cap.ROUND);    // 使终点是圆点
        canvas.drawCircle(getWidth()/2,getHeight()/2,RADIUS,paint);
        canvas.drawArc(getWidth()/2-RADIUS,getHeight()/2-RADIUS,
                getWidth()/2+RADIUS,getHeight()/2+RADIUS,
                -90,225,
                false,paint);
    }
}
