package com.example.customview.HenCoderView.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.customview.HenCoderView.Util;
import com.example.customview.R;

/*
* 多行文字绘制（对齐）
* */
public class ImageTextView extends View {
    private static final int RADIUS = (int) Util.dp2px(150);
    private static final int RING_WIDTH = (int) Util.dp2px(20);
    private static final int CIRCLE_COLOR = Color.parseColor("#90A4AE");
    private static final int HIGHTLIGHT_COLOR = Color.parseColor("#FF4081");

    TextPaint paint = new TextPaint();
    Bitmap bitmap;
    String s = "5月1日，北京、上海、广州、九江、唐山等各工" +
            "业城市的工人群众浩浩荡荡地走向街市、举行了声势浩大的游行、集会。李大钊专门在《新青年》上发表了《" +
            "“五一”运动史》，介绍“五一”节的来历和美法等国工人纪念“五一”的活动，号召中国工人把这年的“五一”作为" +
            "觉醒的日期。陈独秀也为庆祝这个节日发表了《上海厚生纱厂湖南女工问题》一文，揭露资本家剥削工人剩余价值的真相。";
    float[] cutWidth = new float[1];


    public ImageTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        bitmap = getAvatar((int) Util.dp2px(100));
        paint.setTextSize(Util.dp2px(12));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap,getWidth()-Util.dp2px(100),100,paint);
        int index = paint.breakText(s,true,getWidth(),cutWidth);    // 此时cutWidth是第一行截取后的宽
        canvas.drawText(s,0, index,
                0,50,paint);
//        StaticLayout staticLayout =  new StaticLayout("5月1日，北京、上海、广州、九江、唐山等各工" +
//                "业城市的工人群众浩浩荡荡地走向街市、举行了声势浩大的游行、集会。李大钊专门在《新青年》上发表了《" +
//                "“五一”运动史》，介绍“五一”节的来历和美法等国工人纪念“五一”的活动，号召中国工人把这年的“五一”作为" +
//                "觉醒的日期。陈独秀也为庆祝这个节日发表了《上海厚生纱厂湖南女工问题》一文，揭露资本家剥削工人剩余价值的真相。",
//                paint, getWidth(), Layout.Alignment.ALIGN_NORMAL,
//                1,0,false);
//        staticLayout.draw(canvas);
    }

    /*
     * 1.获取图片
     * */
    Bitmap getAvatar(int width){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;  // 设置了这个后，下面的获取只会获取宽度和高度，节省了时间
        BitmapFactory.decodeResource(getResources() , R.drawable.img , options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(getResources() , R.drawable.img , options);
    }
}
