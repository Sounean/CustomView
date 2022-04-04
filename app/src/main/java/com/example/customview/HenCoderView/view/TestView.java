package com.example.customview.HenCoderView.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.customview.HenCoderView.Util;

public class TestView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG); // 反锯齿
    Path path = new Path();
    PathMeasure pathMeasure = new PathMeasure();

   /* public TestView(Context context) {
        super(context);
    }*/

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /*
    * 1.保证不会被过多的调用（view测量了十次，若代码写onMeasure里，这函数 则调用了10次，这里若尺寸不变，则不调用）
    * 2.在每次做测量过程（layout）结束后，若实际尺寸改变了，则调用；没变就不调用
    * */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        path.reset();
        path.addRect(getWidth()/2-150,getHeight()/2-300,getWidth()/2
                +150,getHeight()/2,Path.Direction.CCW);
        path.addCircle(getWidth()/2,getHeight()/2,150,
                Path.Direction.CW);    // (cw顺时针，ccw逆时针)最后一个参数是指顺时针逆时针的意思（不同方向算法会不同）
        pathMeasure = new PathMeasure(path,false);  // 不是封闭则做成封闭的
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(path , paint);
        /*
        * WINDING 转圈的
        * EVEN_ODD
        * INVERSE_WINDING
        * INVERSE_EVEN_ODD
        * */
        //path.setFillType(Path.FillType.E);  // 填充（计算结果会收到顺时针还是逆时针渲染影响）
        /*canvas.drawLine(100,100,200,200,paint);     // 直线
        canvas.drawCircle(getWidth()/2,getHeight()/2,200,paint);    // 圆：圆心x、y,半径（单位像素）
        canvas.drawCircle(getWidth()/2, getHeight()/2,
                Util.dp2px(150),paint); // 单位转为dp了，这样子在不同手机上显示大致是一样的*/
    }
}
