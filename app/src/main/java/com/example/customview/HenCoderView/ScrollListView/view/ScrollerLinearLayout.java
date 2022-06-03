package com.example.customview.HenCoderView.ScrollListView.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

import androidx.annotation.Nullable;

/**
 * @Author : Sounean
 * @Time : On 2022-05-05 9:49
 * @Description : 要实现缓慢复位，需要通过系统自带的Scroller这个专门处理滚动效果的工具类
 * 1.创建Scroller对象
 * 2.mScroller.startScroll(...)计算距离
 * 3.computeScroll()去实时移动view（调用了scrollTo）
 * @Warn :
 */
public class ScrollerLinearLayout extends LinearLayout {
    private Scroller mScroller;

    public ScrollerLinearLayout(Context context) {
        super(context);
        init(context);
    }

    public ScrollerLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScrollerLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context, new LinearInterpolator());    // 第二个参数表示以常量速率滑动
    }

    /*
    * 我们自定义的暴露给外面的函数（只需要横向滚动）
    * */
    public void startScroll(int startX,int dx){
        mScroller.startScroll(startX,0,dx,0);   // 从startX位子移动dx距离
        invalidate();   //Scroller只是根据传参计算xy值，没对view进行操作，所以要调用invalidate()
    }

    @Override
    public void computeScroll() {   // 用来判断停止重绘的
        if (mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
        }
        invalidate();
    }
}
