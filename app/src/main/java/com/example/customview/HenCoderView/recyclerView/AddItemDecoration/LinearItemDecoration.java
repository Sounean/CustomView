package com.example.customview.HenCoderView.recyclerView.AddItemDecoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author : Sounean
 * @Time : On 2022-05-06 17:09
 * @Description : LinearItemDecoration
 * 需要重写三个函数getItemOffsets、onDraw、
 * @Warn :getItemOffsets是针对每个Item都会走一次，也就是说每个Item的outRect都可以不同，但是onDraw和onDrawOver
 * 所整个ItemDecoration只执行一次的，并不是针对Item的，所以我们需要在onDraw和onDrawOver中绘图时，一次性将所有Item的ItemDecoration
 * 绘制完成。从上面也可以看出，这里在onDraw函数中绘图时，通过for循环对每一个item画上一个绿色圆。

 */
public class LinearItemDecoration extends RecyclerView.ItemDecoration {

    private Paint mPaint;
    public LinearItemDecoration(){
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
    }

    //主要作用就是给item的四周加上边距，实现的效果类似于margin，将item的四周撑开一些距离，在撑开这些距离后，
    // 我们就可以利用上面的onDraw函数，在这个距离上进行绘图了
    /*
    *outRect:表示在item的上下左右所撑开的距离
    *view:是指当前Item的View对象
    *parent： 是指RecyclerView 本身
    * state:通过State可以获取当前RecyclerView的状态，也可以通过State在RecyclerView各组件间传递参数，
    * 具体的文档，可以参考：https://developer.android.com/reference/android/support/v7/widget/RecyclerView.State
    * */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = 200;
        outRect.bottom = 1;
    }

    /*
    * 获取每个item的高后，去做圆
    * */
    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent,
                       @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        RecyclerView.LayoutManager manager = parent.getLayoutManager(); // parent指RecyclerView对象本身
        for (int i=0;i<childCount;i++){
            View child = parent.getChildAt(i);  // child是指RecyclerView中的item的view对象
            // 这里我们直接得出画出的圆心是因为在getItemOffsets中的偏移是固定的，在实际中需要通过
            int left = manager.getLeftDecorationWidth(child);
            int cx = left/2;
            //int cx = 100;
            int cy = child.getTop()+child.getHeight()/2;
            c.drawCircle(cx,cy,20,mPaint);
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent,
                           @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
