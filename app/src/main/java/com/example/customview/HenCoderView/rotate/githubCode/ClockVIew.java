package com.example.customview.HenCoderView.rotate.githubCode;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.example.customview.R;

import java.util.Calendar;

/**
 * @Author : Sounean
 * @Time : On 2022-06-03 21:39
 * @Description : ClockVIew
 * @Warn :
 */
public class ClockVIew extends View {
    private Bitmap mClockMaskBitmap, mClockBitmap;
    private Paint mPaint;
    private Xfermode mXfermode;
    private RectF mClockRect;
    private float mNowClockAngle;
    public static final int SECOND = 1000;
    public static final int MINUTE = 60 * SECOND;
    private ValueAnimator mClockAnimator;


    public ClockVIew(Context context) {
        super(context);
        init(context);
    }

    public ClockVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ClockVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    // 1.获取需要实现效果的图片和模式
    private void init(Context context) {
        mClockMaskBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.clock_mask);
        mClockBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.clock);

        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);// 通过这个模式来实现图层叠加绘制的效果

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mClockRect = new RectF();// 保存bitmap的大小。
    }

    // 2.0 计算模式   继承View类，重写view类的onMeasure函数，并对warp_content处理（都固定的代码）
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mClockBitmap.getWidth(), mClockBitmap.getHeight());
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mClockBitmap.getWidth(), heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, mClockBitmap.getHeight());
        }else {
            setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
        }
    }

    // 2.1 绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mClockRect.set(0,0,mClockBitmap.getWidth(),mClockBitmap.getHeight());// 保存bitmap的大小。

        canvas.saveLayer(mClockRect,mPaint,Canvas.ALL_SAVE_FLAG);// 图片有透明度，save函数只能存储位子和大小信息。

        canvas.drawBitmap(mClockBitmap, 0,0, mPaint);   //先后绘制需要设置Xfermode的图片
        mPaint.setXfermode(mXfermode);
        //旋转画布
        canvas.rotate(mNowClockAngle, mClockBitmap.getWidth()/2, mClockBitmap.getHeight()/2);// 3.1再画遮罩前将画布旋转mNowClockAngle角度，旋转中心为图片的中心点
        canvas.drawBitmap(mClockMaskBitmap, 0,0, mPaint);
        mPaint.setXfermode(null);

        canvas.restore();//复原画布
    }

    // 3.0 动画效果
    public void performAnimation() {
        if (mClockAnimator != null && mClockAnimator.isRunning()){  // 判断动画是否开始了，如果开始了，就不再重新开始
            return;
        }
        mClockAnimator = ValueAnimator.ofFloat(0, 360); // 角度设置为0到360度
        mClockAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {   // 添加监听，实时监听数值变化，并将当前数值转换成旋转角度。
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //mNowClockAngle = (float) animation.getAnimatedValue() + mInitClockAngle;//getAnimatedValue为获取当前属性值
                mNowClockAngle = (float) animation.getAnimatedValue();//getAnimatedValue为获取当前属性值
                invalidate();
            }
        });
//        mClockAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                mCalendar.setTimeInMillis(System.currentTimeMillis());
//                mInitClockAngle = mCalendar.get(Calendar.SECOND) * (360/60); //每秒6度
//            }
//        });
        mClockAnimator.setDuration(MINUTE);
        mClockAnimator.setInterpolator(new LinearInterpolator());
        mClockAnimator.setRepeatCount(Animation.INFINITE);  // 动画循环为永久
        mClockAnimator.start();
    }
}
