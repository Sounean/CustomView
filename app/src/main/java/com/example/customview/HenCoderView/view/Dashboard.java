package com.example.customview.HenCoderView.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.customview.HenCoderView.Util;

public class Dashboard extends View {
    private static final int ANGLE = 120;   // 设置角度为120度
    private static final float RADIUS = Util.dp2px(150);    //半径
    private static final float LENGTH = Util.dp2px(100);    //指针长度



    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG); // 反锯齿
    Path dash = new Path();
    private PathDashPathEffect effect;



    public Dashboard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    {   // 只写大括号的话，会在super之后执行
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Util.dp2px(2));    // 粗细2dp
        dash.addRect(0,0,Util.dp2px(2),Util.dp2px(10),Path.Direction.CW);   // 最后一个参数表示是顺时针
        Path arc = new Path();
        arc.addArc(getWidth()/2-RADIUS,getHeight()/2-RADIUS,
                getWidth()/2+RADIUS,getHeight()/2+RADIUS,90+ANGLE/2,360-ANGLE);
        PathMeasure pathMeasure = new PathMeasure(arc,false);// 测量弧长，算间隔用;后面false表示不连一起
        this.effect = new PathDashPathEffect(dash, (pathMeasure.getLength()-Util.dp2px(2))/20, 0, PathDashPathEffect.Style
                .ROTATE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawArc(getWidth()/2-RADIUS,getHeight()/2-RADIUS,
                getWidth()/2+RADIUS,getHeight()/2+RADIUS,90+ANGLE/2,360-ANGLE,
        false,paint);   // 画圆弧
        paint.setPathEffect(effect);
        canvas.drawArc(getWidth()/2-RADIUS,getHeight()/2-RADIUS,
                getWidth()/2+RADIUS,getHeight()/2+RADIUS,90+ANGLE/2,360-ANGLE,
                false,paint);   //  画刻度虚线
        paint.setPathEffect(null);

        //第三次绘制，画指针
                canvas.drawLine(getWidth()/2,
                        getHeight()/2,
                        (float) Math.cos(Math.toRadians(getAngleMark(5)))*LENGTH,
                        (float)Math.sin(Math.toRadians(getAngleMark(5)))*LENGTH,
                        paint);
    }

    int getAngleMark(int mark){
        return (int) (90+(float)ANGLE/2 + (360-(float)ANGLE)/20*mark);
    }
}
