package com.example.customview.HenCoderView.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/*
* 仅剩最后的代码未完成，可以直接用git上的
* */
public class TagLayout extends ViewGroup {
    List<Rect> childrenBounds = new ArrayList<>();
    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthUsed = 0;
        int heightUsed = 0;
        int lineWidthUsed = 0;
        int lineMaxHeight = 0;
        int soecMode = MeasureSpec.getMode(widthMeasureSpec);
        int speWidth = MeasureSpec.getSize(widthMeasureSpec);
        for (int i=0;i<getChildCount();i++){
            View child = getChildAt(i);
            measureChildWithMargins(child,widthMeasureSpec,0,
                    heightMeasureSpec,heightUsed);  // 第一个参数是子view，第二个是父的spec，第三个是已经使用的宽度,第四个是高度模式，第五个是用过的高度
            lineWidthUsed = 0;
            heightUsed += lineMaxHeight;
            lineMaxHeight = 0;
            //measureChildWithMargins(child,widthMeasureSpec,0,);

            Rect childBound;
            //childBound.set(widthUsed,heightUsed,widthUsed+child.getMeasuredWidth(),heightUsed+child.getMeasuredHeight());
            widthUsed+=child.getMeasuredWidth();
            lineMaxHeight = Math.max(lineMaxHeight,child.getMeasuredHeight());
        }

        int width = widthUsed;
        int height = lineMaxHeight;
        setMeasuredDimension(width,height);
    }

    // 原理是遍历每个子view然后把宽高给他们
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {  //此处的ltrb是由taglayout的来定，他设了match那就是全屏
        for (int i = 0;i<getChildCount();i++){
            View child = getChildAt(i);
            Rect childBounds = childrenBounds.get(i);
            child.layout(childBounds.left,childBounds.top,childBounds.right,childBounds.bottom);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }
}
