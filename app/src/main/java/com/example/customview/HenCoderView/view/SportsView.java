package com.example.customview.HenCoderView.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.customview.HenCoderView.Util;

public class SportsView extends View {
    private static final int RADIUS = (int) Util.dp2px(150);
    private static final int RING_WIDTH = (int) Util.dp2px(20);
    private static final int CIRCLE_COLOR = Color.parseColor("#90A4AE");
    private static final int HIGHTLIGHT_COLOR = Color.parseColor("#FF4081");

    Rect rect = new Rect();
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint.FontMetrics fontMetrics = new Paint.FontMetrics();



    public SportsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setTextSize(Util.dp2px(100));
        //paint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "字体的名字"));    // 修改字体(这里放字体)
        paint.setTextAlign(Paint.Align.CENTER); //居中
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制环
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(CIRCLE_COLOR);
        paint.setStrokeWidth(RING_WIDTH);
        canvas.drawCircle(getWidth()/2,getHeight()/2,RADIUS,paint);
        //绘制进度条
        paint.setColor(HIGHTLIGHT_COLOR);
        paint.setStrokeCap(Paint.Cap.ROUND);    // 使终点是圆点
        canvas.drawArc(getWidth()/2-RADIUS,getHeight()/2-RADIUS,
                getWidth()/2+RADIUS,getHeight()/2+RADIUS,
                -90,255,
                false,paint);
        // 绘制文字
        paint.setStyle(Paint.Style.FILL);   // 不设置的话会变成空心
        // 字体内容，字体位子，画笔(方式一居中)
        /*paint.getTextBounds("abab",0,"abab".length(),rect);
        int offset = (rect.top+rect.bottom)/2;
        canvas.drawText("abab" , getWidth()/2,getHeight()/2-offset,paint);  // 减去偏移量把baseline偏移正常。*/
        // 方式二居中
        paint.getFontMetrics(fontMetrics);
        float offset = (fontMetrics.ascent + fontMetrics.descent)/2;
        canvas.drawText("abab" , getWidth()/2,getHeight()/2-offset,paint);

    }
}
