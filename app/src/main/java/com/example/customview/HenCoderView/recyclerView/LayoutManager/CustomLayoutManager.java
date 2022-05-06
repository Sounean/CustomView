package com.example.customview.HenCoderView.recyclerView.LayoutManager;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author : Sounean
 * @Time : On 2022-05-06 22:49
 * @Description : CustomLayoutManager
 * @Warn :继承LayoutManager时，只会自动去生成generateDefaultLayoutParams方法。
 * 还需要自己添加onLayoutChildren去添加view，添加canScrollVertically去让LayoutManager可以垂直滚动(想让可以横向滚动就通过在canScrollHorizontally()中return true)
 * 添加scrollVerticallyBy中接受每次滚动的距离dy.
 * 除此之外需要一个全局变量mSumDy来计算是否到顶，（通过scrollVerticallyBy的实参dy）
 * 需要一个全局变量mTotalHeight来计算是否到底.（在onLayoutChildren中我们有去测量所有的item，在这里去计算所有的item高）
 */
public class CustomLayoutManager extends RecyclerView.LayoutManager {
    private int mSumDy = 0; //保存所有移动过的dy
    private int mTotalHeight = 0;// 保存所有的item高度

    /*
    * 要强制实现这个。
    * 这个方法就是RecyclerView Item的布局参数，换种说法，就是RecyclerView 子 item 的 LayoutParameters，
    * 若是想修改子Item的布局参数（比如：宽/高/margin/padding等等），那么可以在该方法内进行设置。
    * 一般来说，没什么特殊需求的话，则可以直接让子item自己决定自己的宽高即可（wrap_content）。
    * */
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    /*
    * 1.添加view
    * 如果用了自定义的LayoutManager，那么要自己写下面这个方法把view加进来
    * 因为所有的Item的布局都是在LayoutManager中处理的，很明显，我们目前在CustomLayoutManager中并没有布局任何的Item。当然没有Item出现了
    * */
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        //定义竖直方向的偏移量
        int offsetY = 0;
        for (int i = 0; i < getItemCount(); i++) {
            View view = recycler.getViewForPosition(i);
            addView(view);  // 1/把所有的item对应的view加进来
            measureChildWithMargins(view, 0, 0);    //2.测量view
            int width = getDecoratedMeasuredWidth(view);
            int height = getDecoratedMeasuredHeight(view);
            layoutDecorated(view, 0, offsetY, width, offsetY + height); //3.把所有的item摆放那个在它应在的位子
            offsetY += height;
        }
        //如果所有子View的高度和没有填满RecyclerView的高度，
        // 则将高度设置为RecyclerView的高度
        mTotalHeight = Math.max(offsetY, getVerticalSpace());
    }
    private int getVerticalSpace() {    //返回RecyclerView用于显示item的真实高度
        return getHeight() - getPaddingBottom() - getPaddingTop();
    }


    /*
    * 2.添加垂直滚动功能，且接受每次滚动的恶距离dy
    * 在scrollVerticallyBy中,dy表示手指在屏幕上每次滑动的位移（当手指由下往上滑时,dy>0；反之dddd）
    * */
    @Override
    public boolean canScrollVertically() {
        return true;
    }
    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler,
                                  RecyclerView.State state) {
        int travel = dy;
        //如果滑动到最顶部
        if (mSumDy + dy < 0) {  // 如果当前移动的距离小于0，那么不再累加dy，并使容器移动到y=0的位子
            travel = -mSumDy;
        }else if (mSumDy + dy > mTotalHeight - getVerticalSpace()) {//mSumDy + dy表示当前移动的距离，mTotalHeight - getVerticalSpace()表示当滑动到底时滚动的总距离
            travel = mTotalHeight - getVerticalSpace() - mSumDy;
        }
        mSumDy += travel;
        offsetChildrenVertical(-travel);    // 平移容器内的item
        return dy;
    }
}
