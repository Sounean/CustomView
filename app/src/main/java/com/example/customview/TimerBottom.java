package com.example.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Timer;

public class TimerBottom extends androidx.appcompat.widget.AppCompatButton {


    /*
     * 关于倒数计时的实现
     * */
    private static Handler mHandler;
    private int totalDuration = 60*1000;//验证码时长60秒
    private int dTime = 1000;//时间间隔1秒
    private int restTime = totalDuration;//剩余时间
    private int duration;
    private OnClickListener onClickListener;
    private int count;
    private final Context context;
    private Timer timer = new Timer();

    public TimerBottom(Context context) {
        this(context, null);
    }

    public TimerBottom(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("NonConstantResourceId")
    public TimerBottom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        //final Resources.Theme theme = context.getTheme();
        TypedArray appearance = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.TimerBottom, defStyleAttr, 0);
        if (appearance != null) {
            int n = appearance.getIndexCount();
            for (int i = 0; i < n; i++) {
                int attr = appearance.getIndex(i);
                if (attr == R.styleable.TimerBottom_duration_bottom) {
                    duration = appearance.getColor(attr, duration);
                }
            }
        }

        assert appearance != null;
        appearance.recycle();
    }



   /* @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(new SuperOnClickListener(l));
    }

    private class SuperOnClickListener implements OnClickListener {
        OnClickListener mOnClickListener;
        public SuperOnClickListener(OnClickListener l) {
            mOnClickListener = l;
        }

        @Override
        public void onClick(View view) {
            mOnClickListener.onClick(view);
            Log.e("--------------------->", "里面监听到被点击");
        }
    }*/


    public void startCountDown() {
        Log.e("---------------------> ", "4");
        mHandler = new Handler();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                restTime -= dTime;
                if(restTime > 0) {
                    //setEnabled(false);
                    mHandler.postDelayed(this,dTime);
                    setText("RESEND(" + restTime/1000 + "s)");
                    //postInvalidate();
                    //viewBinding.btnPickupResendCode.setText("RESEND(" + restTime/1000 + "s)");
                } else {
                    setText("SEND(60s)");
                    restTime = totalDuration;
                    setEnabled(true);
                }
            }
        });
    }

}
