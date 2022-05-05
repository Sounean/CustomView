package com.example.customview.HenCoderView.touch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.customview.HenCoderView.Util;

/**
 * @Author : Sounean
 * @Time : On 2022-05-03 16:32
 * @Description : ScalableImageView
 * @Warn :
 */
public class ScalableImageView extends View {
    private static final float IMAGE_WIDTH = Util.dp2px(300);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;

    float offsetX;
    float offsetY;

    public ScalableImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        bitmap = Util.getAvatar(getResources(), (int) IMAGE_WIDTH);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        offsetX = (getWidth()-bitmap.getWidth())/2;
        offsetY = (getHeight()-bitmap.getHeight())/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,offsetX,offsetY,paint);
    }
}
