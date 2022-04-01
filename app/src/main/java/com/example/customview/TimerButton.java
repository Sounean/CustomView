package com.example.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TimerButton extends androidx.appcompat.widget.AppCompatButton {

    /*
     * 关于倒数计时的实现
     * */
    private static Handler mHandler;
    private int totalDuration = 60*1000;//验证码时长60秒
    private int dTime = 1000;//时间间隔1秒
    private int restTime = totalDuration;//剩余时间
    int mTimer;
    String mText;
    private Paint mPaint;

    public TimerButton(@NonNull Context context)
    {
        this(context, null);
    }

    public TimerButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimerButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TimerButtom, defStyle, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++)
        {
            int attr = a.getIndex(i);
            switch (attr)
            {
                case R.styleable.TimerButtom_send_time:
                    mTimer = a.getInt(attr,60);
                    break;
                case R.styleable.TimerButtom_send_text:
                    mText = a.getString(attr);
                    break;
            }
        }

        a.recycle();

        mPaint = new Paint();
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(new TimerButton.SuperOnClickListener(l));
    }

    private class SuperOnClickListener implements OnClickListener {
        OnClickListener mOnClickListener;
        public SuperOnClickListener(OnClickListener l) {
            mOnClickListener = l;
        }

        @Override
        public void onClick(View view) {
            mOnClickListener.onClick(view);
            Log.e("---------------------> ", "里面监听到被点击");
        }
    }

    private void startCountDown() {
        mHandler = new Handler();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                restTime -= dTime;
                if(restTime > 0) {
                    //.setEnabled(false);
                    mHandler.postDelayed(this,dTime);
                    mText = "RESEND(" + restTime/1000 + "s)";
                    postInvalidate();
                    //viewBinding.btnPickupResendCode.setText("RESEND(" + restTime/1000 + "s)");
                } else {
                    mText = "SEND(60s)";
                    //viewBinding.btnPickupResendCode.setText("SEND(60s)");
                    restTime = totalDuration;
                    //viewBinding.btnPickupResendCode.setEnabled(true);
                }
            }
        });
    }
}
