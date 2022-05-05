package com.example.customview.HenCoderView.conflict.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.example.customview.R;

/**
 * @Author : Sounean
 * @Time : On 2022-05-04 17:08
 * @Description : CustomScrollVIew
 * @Warn :
 */
public class CustomScrollVIew extends ScrollView {
    private float mDownPointY;
    private int mConflictHeight;

    public CustomScrollVIew(Context context) {
        super(context);
        init(context);
    }

    public CustomScrollVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomScrollVIew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mConflictHeight = context.getResources().getDimensionPixelSize(R.dimen.conflict_height);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                intercepted =false;
                float y = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (mDownPointY<mConflictHeight){
                    intercepted = false;
                }else {
                    intercepted = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercepted = false;
                break;
            default:break;
        }
        return intercepted;
    }
}
