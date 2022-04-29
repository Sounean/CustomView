package com.example.customview.HenCoderView.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.customview.HenCoderView.Util;


/*
* 饼状图
* */
public class PieChart extends View {
    private static final int RADIUS = (int) Util.dp2px(150);
    private static final int LENGTH = (int) Util.dp2px(20);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG); // 反锯齿
    RectF bounds = new RectF();


    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        bounds.set(getWidth()/2-RADIUS,getHeight()/2-RADIUS,getWidth()/2+RADIUS,
                getHeight()/2+RADIUS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.parseColor("#2979FF"));
        canvas.drawArc(bounds,0,60,true,paint);
        paint.setColor(Color.parseColor("#29FF00"));
        canvas.drawArc(bounds,60,60,true,paint);
       // canvas.translate(Math.cos(Math.toRadians(currentAngle)));
        paint.setColor(Color.parseColor("#2970FF"));
        canvas.drawArc(bounds,120,60,true,paint);
        paint.setColor(Color.parseColor("#2953FF"));
        canvas.drawArc(bounds,180,60,true,paint);
    }
}
