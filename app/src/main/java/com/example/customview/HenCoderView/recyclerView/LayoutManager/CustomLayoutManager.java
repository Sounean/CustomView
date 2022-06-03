package com.example.customview.HenCoderView.recyclerView.LayoutManager;

import android.graphics.Rect;
import android.util.SparseArray;
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
    private int mItemWidth,mItemHeight; // 回收复用时要计算的一屏多少item数,需要保存item高度
    private SparseArray<Rect> mItemRects = new SparseArray<>(); // 存放item的位子（关于xy和宽高）

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
        if (getItemCount() == 0) {//adapter中没数据时，直接把当前所有的item从屏幕上剥离，清空屏幕。
            detachAndScrapAttachedViews(recycler);
            return;
        }
        detachAndScrapAttachedViews(recycler);
        //随便向系统申请一个HolderView，然后测量它的宽度、高度，并计算可见的Item数
        View childView = recycler.getViewForPosition(0);
        measureChildWithMargins(childView, 0, 0);// 只有调用这个测量一次系统才知道它的宽高
        mItemWidth = getDecoratedMeasuredWidth(childView);
        mItemHeight = getDecoratedMeasuredHeight(childView);

        int visibleCount = getVerticalSpace() / mItemHeight;//

        //定义竖直方向的偏移量
        int offsetY = 0;
//            for (int i = 0; i < getItemCount(); i++) {// 最原先 没有回收复用直接摆放的代码
//                View view = recycler.getViewForPosition(i);
//                addView(view);  // 1/把所有的item对应的view加进来
//                measureChildWithMargins(view, 0, 0);    //2.测量view
//                int width = getDecoratedMeasuredWidth(view);
//                int height = getDecoratedMeasuredHeight(view);
//                layoutDecorated(view, 0, offsetY, width, offsetY + height); //3.把所有的item摆放那个在它应在的位子
//                offsetY += height;
//                offsetY += height;
//            }
        for (int i = 0; i < getItemCount(); i++) {  //计算出所有的item的位子并保存到mItemRects.
            Rect rect = new Rect(0, offsetY, mItemWidth, offsetY + mItemHeight);
            mItemRects.put(i, rect);
            offsetY += mItemHeight;
        }
        for (int i = 0; i < visibleCount; i++) { //所有可视范围内item进行添加，绘制以及放置.
            Rect rect = mItemRects.get(i);
            View view = recycler.getViewForPosition(i);
            addView(view);
            //addView后一定要measure，先measure再layout
            measureChildWithMargins(view, 0, 0);
            layoutDecorated(view, rect.left, rect.top, rect.right, rect.bottom);
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
    * 在scrollVerticallyBy中,dy表示手指在屏幕上每次滑动的位移（当手指由下往上滑时,dy>0；反之dddd）.在下方中，将travel来代表最终滑动方向
    * */
    @Override
    public boolean canScrollVertically() {
        return true;
    }
//    @Override // 原先的没有回收复用功能的函数
//    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler,
//                                  RecyclerView.State state) {
//        int travel = dy;
//        //如果滑动到最顶部
//        if (mSumDy + dy < 0) {  // 如果当前移动的距离小于0，那么不再累加dy，并使容器移动到y=0的位子
//            travel = -mSumDy;
//        }else if (mSumDy + dy > mTotalHeight - getVerticalSpace()) {//mSumDy + dy表示当前移动的距离，mTotalHeight - getVerticalSpace()表示当滑动到底时滚动的总距离
//            travel = mTotalHeight - getVerticalSpace() - mSumDy;
//        }
//        mSumDy += travel;
//        offsetChildrenVertical(-travel);    // 平移容器内的item
//        return dy;
//    }
    @Override// 添加了回收复用功能的函数
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() <= 0) {
            return dy;
        }

        int travel = dy;
        //如果滑动到最顶部
        if (mSumDy + dy < 0) {
            travel = -mSumDy;
        } else if (mSumDy + dy > mTotalHeight - getVerticalSpace()) {
            //如果滑动到最底部
            travel = mTotalHeight - getVerticalSpace() - mSumDy;
        }

        // 回收越界子View
        for (int i = getChildCount() - 1; i >= 0; i--) {
            View child = getChildAt(i);
            if (travel > 0) {//需要回收当前屏幕，上越界的View
                if (getDecoratedBottom(child) - travel < 0) {
                    removeAndRecycleView(child, recycler);
                    continue;
                }
            } else if (travel < 0) {//回收当前屏幕，下越界的View
                if (getDecoratedTop(child) - travel > getHeight() - getPaddingBottom()) {
                    removeAndRecycleView(child, recycler);
                    continue;
                }
            }
        }

        Rect visibleRect = getVisibleArea(travel);
        //布局子View阶段
        if (travel >= 0) {
            View lastView = getChildAt(getChildCount() - 1);
            int minPos = getPosition(lastView) + 1;//从最后一个View+1开始吧

            //顺序addChildView
            for (int i = minPos; i <= getItemCount() - 1; i++) {
                Rect rect = mItemRects.get(i);
                if (Rect.intersects(visibleRect, rect)) {
                    View child = recycler.getViewForPosition(i);
                    addView(child);
                    measureChildWithMargins(child, 0, 0);
                    layoutDecorated(child, rect.left, rect.top - mSumDy, rect.right, rect.bottom - mSumDy);
                } else {
                    break;
                }
            }
        } else {
            View firstView = getChildAt(0);
            int maxPos = getPosition(firstView) - 1;

            for (int i = maxPos; i >= 0; i--) {
                Rect rect = mItemRects.get(i);
                if (Rect.intersects(visibleRect, rect)) {
                    View child = recycler.getViewForPosition(i);
                    addView(child, 0);//将View添加至RecyclerView中，childIndex为1，但是View的位置还是由layout的位置决定
                    measureChildWithMargins(child, 0, 0);
                    layoutDecoratedWithMargins(child, rect.left, rect.top - mSumDy, rect.right, rect.bottom - mSumDy);
                } else {
                    break;
                }
            }
        }

        mSumDy += travel;
        // 平移容器内的item
        offsetChildrenVertical(-travel);
        return travel;
    }
    private Rect getVisibleArea(int travel) {   // 新增移动trave时，当前屏幕的位子
        Rect result = new Rect(getPaddingLeft(), getPaddingTop() + mSumDy + travel, getWidth() + getPaddingRight(), getVerticalSpace() + mSumDy + travel);
        return result;
    }


}
