//
//
//import android.animation.Animator;
//import android.animation.Animator.AnimatorListener;
//import android.animation.AnimatorListenerAdapter;
//import android.animation.ValueAnimator;
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.PorterDuff;
//import android.graphics.PorterDuffXfermode;
//import android.graphics.Rect;
//import android.graphics.RectF;
//import android.graphics.Xfermode;
//
//import android.util.AttributeSet;
//import android.view.View;
//import android.view.animation.Animation;
//import android.view.animation.LinearInterpolator;
//
//
//
//import java.util.Calendar;
//
//public class ClockImageView extends View {
//    private Bitmap mClockMaskBitmap, mClockBitmap;
//    private Paint mPaint;
//    private Xfermode mXfermode;
//    private RectF mClockRect;
//    public static final int SECOND = 1000;
//    public static final int MINUTE = 60 * SECOND;
//    private float mNowClockAngle;
//    private ValueAnimator mClockAnimator;
//    private float mInitClockAngle;
//    private Calendar mCalendar;
//    private int mTextSize,mTextColor;
//    private int mDigitalTimeTextStartX;
//    private int mDigitalTimeTextStartY;
//    private String mLastDigitalTimeStr;
//
//    public ClockImageView(Context context) {
//        super(context);
//        init(context);
//    }
//
//    public ClockImageView(Context context, @Nullable AttributeSet attrs) {
//        this(context,attrs,0);
//    }
//
//    public ClockImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//
//        TypedArray typedArray = context.obtainStyledAttributes(
//                attrs, R.styleable.ClockImageView);
//        mTextSize = typedArray.getDimensionPixelSize(R.styleable.ClockImageView_timeTextSize,
//               dipToPx(context, 40));
//        mTextColor = typedArray.getColor(R.styleable.ClockImageView_timeTextColor,
//                Color.RED);
//        typedArray.recycle();
//
//        init(context);
//    }
//
//
//
//    private void init(Context context) {
//
//
//
//        mClockMaskBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.clock_mask);
//        mClockBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.clock);
//
//        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);// 通过这个模式来实现图层叠加绘制的效果
//
//        mPaint = new Paint();
//        mPaint.setAntiAlias(true);
//
//        mClockRect = new RectF();
//
//        mCalendar = Calendar.getInstance();
//
//        String defalutText = "00:00:00";
//        Rect timeTextRect = new Rect();
//        mPaint.setTextSize(dipToPx(context,mTextSize));
//        mPaint.getTextBounds(defalutText, 0,
//                defalutText.length(), timeTextRect);
//
//        mDigitalTimeTextStartX = mClockBitmap.getWidth()/2 - timeTextRect.width() / 2;
//        mDigitalTimeTextStartY = mClockBitmap.getHeight()/2;
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//
//        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
//            setMeasuredDimension(mClockBitmap.getWidth(), mClockBitmap.getHeight());
//        } else if (widthMode == MeasureSpec.AT_MOST) {
//            setMeasuredDimension(mClockBitmap.getWidth(), heightSize);
//        } else if (heightMode == MeasureSpec.AT_MOST) {
//            setMeasuredDimension(widthSize, mClockBitmap.getHeight());
//        }else {
//            setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
//        }
//    }
//
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
//        mClockRect.set(0,0,mClockBitmap.getWidth(),mClockBitmap.getHeight());// 保存bitmap的大小。
//
//        canvas.saveLayer(mClockRect,mPaint,Canvas.ALL_SAVE_FLAG);// 图片有透明度，save函数只能存储位子和大小信息。
//
//        canvas.drawBitmap(mClockBitmap, 0,0, mPaint);
//        mPaint.setXfermode(mXfermode);
//        //旋转画布
//        canvas.rotate(mNowClockAngle, mClockBitmap.getWidth()/2, mClockBitmap.getHeight()/2);
//        canvas.drawBitmap(mClockMaskBitmap, 0,0, mPaint);
//        mPaint.setXfermode(null);
//
//        canvas.restore();
//
//        updateTimeText(canvas);
//
//    }
//
//    private void updateTimeText(Canvas canvas) {
//        long currentTimeMillis = System.currentTimeMillis();
//        // Use of abs() func is to prevent the user to adjust the time forward.
//        mCalendar.setTimeInMillis(currentTimeMillis);
//        mLastDigitalTimeStr = String.format("%02d:%02d:%02d",
//                mCalendar.get(Calendar.HOUR), mCalendar.get(Calendar.MINUTE),mCalendar.get(Calendar.SECOND));
//        mPaint.setColor(mTextColor);
//        canvas.drawText(mLastDigitalTimeStr, mDigitalTimeTextStartX, mDigitalTimeTextStartY, mPaint);
//    }
//
//
//    public void performAnimation() {
//        if (mClockAnimator != null && mClockAnimator.isRunning()){  // 判断动画是否开始了，如果开始了，就不再重新开始
//            return;
//        }
//        mClockAnimator = ValueAnimator.ofFloat(0, 360); // 角度设置为0到360度
//        mClockAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {   // 添加监听，实时监听数值变化，并将当前数值转换成旋转角度。
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                mNowClockAngle = (float) animation.getAnimatedValue() + mInitClockAngle;//getAnimatedValue为获取当前属性值
//                invalidate();
//            }
//        });
//        mClockAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                mCalendar.setTimeInMillis(System.currentTimeMillis());
//                mInitClockAngle = mCalendar.get(Calendar.SECOND) * (360/60); //每秒6度
//            }
//        });
//        mClockAnimator.setDuration(MINUTE);
//        mClockAnimator.setInterpolator(new LinearInterpolator());
//        mClockAnimator.setRepeatCount(Animation.INFINITE);  // 动画循环为永久
//        mClockAnimator.start();
//    }
//
//    public static int dipToPx(Context context, int dip) {
//        float px = context.getResources().getDisplayMetrics().density;
//        return (int) (dip * px + 0.5f);
//    }
//
//}
