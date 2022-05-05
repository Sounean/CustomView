package com.example.customview.HenCoderView.ScrollListView.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.LinearInterpolator;
import android.widget.ListView;
import android.widget.Scroller;

import com.example.customview.HenCoderView.ScrollListView.adapter.DelScrollListViewAdapter;

/**
 * @Author : Sounean
 * @Time : On 2022-05-05 11:29
 * @Description : DelScrollListViewAdapter
 * @Warn :
 */
public class DelScrollListView extends ListView {
    private itemScrollLinearLayout mCurView;
    private int mlastX = 0;
    private final int MAX_WIDTH = 200;
    private Context mContext;
    private final Scroller mScroller;

    public DelScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mScroller = new Scroller(context, new LinearInterpolator(context, null));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int maxLength = dipToPx(mContext, MAX_WIDTH);
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                //点击了哪一行
                int position = pointToPosition(x, y);   // 根据xy获取在listview中的位子
                if (position != INVALID_POSITION) {
                    DelScrollListViewAdapter.DataHolder data = (DelScrollListViewAdapter.DataHolder) getItemAtPosition(position);
                    mCurView = data.rootView;
                }
            }
            break;
            default:break;
        }
        if (mCurView != null){  // 当确实滑动在子view（即item）中时，则让子view消费点击事件
            mCurView.disPatchTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }


    private int dipToPx(Context context, int dip) {
        return (int) (dip * context.getResources().getDisplayMetrics().density + 0.5f);
    }

}
