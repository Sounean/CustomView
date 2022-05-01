package com.example.customview.HenCoderView.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.customview.HenCoderView.Util;

/**
 * @Author : Sounean
 * @Time : On 2022-05-01 22:36
 * @Description : CircleVIew
 * @Warn :
 */
public class CircleVIew extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float radius = Util.dp2px(50);

    public CircleVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setColor(Color.RED);
    }

    public float getRadius() {
        return radius;
    }
    public void setRadius(float radius) {
        this.radius = radius;
        invalidate();   // 需要添加这个，通知界面有被改变，才能调用onDraw()
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(getWidth()/2,getHeight()/2,radius,paint);
    }
}
