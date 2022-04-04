package com.example.customview.HenCoderView;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/*
* 像素转成px
* */
public class Util {
    public static float dp2px(float dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,
                Resources.getSystem().getDisplayMetrics());
    }
}
