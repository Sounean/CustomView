package com.example.customview.HenCoderView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.TypedValue;

import com.example.customview.R;

/*
* 像素转成px
* */
public class Util {
    public static float dp2px(float dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,
                Resources.getSystem().getDisplayMetrics());
    }

    /*
     * 1.获取图片
     * */
    public static Bitmap getAvatar(Resources resources,int width){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;  // 设置了这个后，下面的获取只会获取宽度和高度，节省了时间
        BitmapFactory.decodeResource(resources , R.drawable.img , options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(resources , R.drawable.img , options);
    }

    public static int getZForCamera(int i){
        return (int) (i * Resources.getSystem().getDisplayMetrics().density);
    }
}
