package com.example.customview.HenCoderView.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.customview.HenCoderView.Util;
import com.example.customview.R;

/*
* 头像
* */
public class AvatarView extends View {
    private static final float WIDTH = Util.dp2px(150);
    private static final float PADDING = Util.dp2px(50);
    private static final float EDGE_WIDTH = Util.dp2px(10);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    Bitmap bitmap;
    RectF savedArea = new RectF();


    public AvatarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        bitmap = getAvatar((int)WIDTH);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        savedArea.set(PADDING,PADDING,PADDING+WIDTH,PADDING+WIDTH); // 意思是扣这个部分的区域，这样子缓冲可以尽可能的小
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawOval(PADDING , PADDING,PADDING+WIDTH,PADDING+WIDTH,paint);
        int saved = canvas.saveLayer(savedArea,paint);
        canvas.drawOval(PADDING +EDGE_WIDTH, PADDING+EDGE_WIDTH,
                PADDING+WIDTH-EDGE_WIDTH,PADDING+WIDTH-EDGE_WIDTH,paint);   //一个圆
        paint.setXfermode(xfermode);    // 这个模式会让下方的图片被上面的圆切掉
        canvas.drawBitmap(bitmap,PADDING,PADDING,paint);    // 显示的图片，左间距，右间距
        paint.setXfermode(null);
        canvas.restoreToCount(saved);
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
