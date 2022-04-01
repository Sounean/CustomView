package com.example.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BottomTest extends androidx.appcompat.widget.AppCompatButton{

    private String mTest;   // 从属性集方式得到的文字
    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;
    private Paint mPaint;

    public BottomTest(@NonNull Context context)
    {
        this(context, null);
    }

    public BottomTest(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("NonConstantResourceId")
    public BottomTest(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context,
                attrs,
                defStyle);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ButtomTest, defStyle, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++)
        {
            int attr = a.getIndex(i);
            switch (attr)
            {
                case R.styleable.ButtomTest_buttom_demo:
                    mTest = a.getString(attr);
                    break;

            }
        }
        a.recycle();

        /**
         * 获得绘制文本的宽和高
         */
        mPaint = new Paint();
        mPaint.setTextSize(30);
        // mPaint.setColor(mTitleTextColor);
        mBound = new Rect();
        mPaint.getTextBounds(mTest, 0, mTest.length(), mBound);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.YELLOW);   // 获取字体颜色
        canvas.drawText(mTest, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);    // 绘制字体
    }
}
