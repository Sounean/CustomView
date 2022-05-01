package com.example.customview.HenCoderView.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.customview.HenCoderView.Util;
import com.example.customview.R;

/**
 * @Author : Sounean
 * @Time : On 2022-05-01 17:11
 * @Description : clipVIew
 * @Warn :
 */
public class clipVIew extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public clipVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.clipRect(0,0,200,200);   //clipOutRect()和这个函数刚好相反
        canvas.drawBitmap(Util.getAvatar(getResources(),400),
                0,0,paint);
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
