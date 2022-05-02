package com.example.customview.HenCoderView.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;


/*
* 简单改写已有的view尺寸 本来长方形的改成正方形的，且不影响排放
* */
public class SquareImageView extends androidx.appcompat.widget.AppCompatImageView {
    public SquareImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);   // 1.通过父view的方法保存当前的预计宽高
        int measuredWidth = getMeasuredWidth(); // 2.获取保存的值
        int measuredHeight = getMeasuredHeight();
        int size = Math.max(measuredWidth,measuredHeight);

        setMeasuredDimension(size,size);    // 3.把宽和高保存了。（此处不是直接给父view，而是存起来，让父view去拿）
    }

    //    @Override
//    public void layout(int l, int t, int r, int b) {    // 错误的地方写拉伸会导致无法通知父view去重新排放而错误显示
//        int width = r-l;
//        int height = b-t;
//        int size = Math.max(width,height);
//
//        // 自定义成了一个正方形
//        super.layout(l, t, l+size, t+size);   // 此处会在view里setFrame方法里存上这四个值
//    }
}
