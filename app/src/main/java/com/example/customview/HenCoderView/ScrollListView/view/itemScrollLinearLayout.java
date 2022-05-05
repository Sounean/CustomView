package com.example.customview.HenCoderView.ScrollListView.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * 因为要实现移动下一个item时，上一个item要缩回去，但是每次缩回去都有一段时延，
 * 在时延内快速多次点击会导致之前的item还没缩完，在computeScroll()中的scroller对象就被替换成下一次的了。
 * 所以这个效果由item自己实现
 */
public class itemScrollLinearLayout extends LinearLayout {
    private int mlastX = 0;
    private final int MAX_WIDTH = 200;
    private Context mContext;
    private Scroller mScroller;
    private OnScrollListener mScrollListener;

    public void setOnScrollListener(OnScrollListener scrollListener) {  // 1.设置监听器
        mScrollListener = scrollListener;
    }

    public void smoothScrollTo(int destX, int destY) {  //缓慢将ITEM滚动到指定位置（配合computeScroll）
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        mScroller.startScroll(scrollX, 0, delta, 0);
        invalidate();
    }

    public static interface OnScrollListener {  // 监听当前item是否被全部拉了出来
        public void OnScroll(itemScrollLinearLayout view);  // 提供目前伸展出来的，下一个item出来时就把这个缩回去
    }

    public itemScrollLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mScroller = new Scroller(context, new LinearInterpolator(context, null));
    }

    public void disPatchTouchEvent(MotionEvent event) {
        int maxLength = dipToPx(mContext, MAX_WIDTH);

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
            }
            break;
            case MotionEvent.ACTION_MOVE: {
                int scrollX = this.getScrollX();
                int newScrollX = scrollX + mlastX - x;
                if (newScrollX < 0) {
                    newScrollX = 0;
                } else if (newScrollX > maxLength) {
                    newScrollX = maxLength;
                }
                this.scrollTo(newScrollX, 0);
            }
            break;
            case MotionEvent.ACTION_UP: {
                int scrollX = this.getScrollX();
                int newScrollX = scrollX + mlastX - x;
                if (scrollX > maxLength / 2) {
                    newScrollX = maxLength;
                    mScrollListener.OnScroll(this);     // 当完全展开时，通知出去
                } else {
                    newScrollX = 0;
                }
                mScroller.startScroll(scrollX, 0, newScrollX - scrollX, 0);
                invalidate();
            }
            break;
        }
        mlastX = x;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            this.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
        }
        invalidate();
    }

    private int dipToPx(Context context, int dip) {
        return (int) (dip * context.getResources().getDisplayMetrics().density + 0.5f);
    }
}
