package com.example.customview.HenCoderView.view;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.customview.HenCoderView.Util;

/**
 * @Author : Sounean
 * @Time : On 2022-05-01 17:44
 * @Description : CameraVIew
 * @Warn :
 */
public class CameraVIew extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Camera camera = new Camera();

    public CameraVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        camera.rotateX(45);   // 让camera沿着x轴转30度
        camera.setLocation(0,0,Util.getZForCamera(-6));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制上半部分
        canvas.save();
        canvas.rotate(-45);
        canvas.clipRect(0,100,400,100+400/2);    //切出上半部分
//        canvas.translate(100+400/2,100+400/2);  // 把图像中心移动到投射点正下方，即原点正下方，移动的距离应该为图像的偏移加上图像宽度的一半，高同理。
//        canvas.translate(-(100+400/2),-(100+400/2));
        canvas.rotate(45);
        canvas.drawBitmap(Util.getAvatar(getResources(),400), 100,100,paint);
        canvas.restore();


        // 绘制下半部分
        canvas.save();
        canvas.clipRect(0,100+400/2,getWidth(),getHeight());    //切除上半部分
        canvas.translate(100+400/2,100+400/2);  // 把图像中心移动到投射点正下方，即原点正下方，移动的距离应该为图像的偏移加上图像宽度的一半，高同理。
        canvas.rotate(-45);
        camera.applyToCanvas(canvas);   // 进行交互到canvas
        canvas.rotate(45);
        canvas.translate(-(100+400/2),-(100+400/2));
        canvas.drawBitmap(Util.getAvatar(getResources(),400), 100,100,paint);
        canvas.restore();
    }
}
