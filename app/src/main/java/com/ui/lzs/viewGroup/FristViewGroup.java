package com.ui.lzs.viewGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lzs on 2015/12/16.
 */
public class FristViewGroup extends ViewGroup {

    private int width, height;

    public FristViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs); //返回带margin的LayoutParams;
    }
    //计算出需要的土地大小

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widhtMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);//自定义viewgroup的时候需要测量子view！！！
        int a = getChildCount();
        MarginLayoutParams mlp = null;
        int width1 = 0, width2 = 0, height1 = 0, height2 = 0;
        for (int i = 0; i < a; i++) {
            View v = getChildAt(i);
            mlp = (MarginLayoutParams) v.getLayoutParams();

            if (i == 0 || i == 1) {

                width1 += v.getMeasuredWidth() + mlp.leftMargin + mlp.rightMargin;
            } if (i == 2 || i == 3) {

                width2 += v.getMeasuredWidth() + mlp.leftMargin + mlp.rightMargin;
            }  if (i == 0 || i == 2) {

                height1 += v.getMeasuredHeight() + mlp.topMargin + mlp.bottomMargin;

            }  if (i == 1 || i == 3) {
                height2 += v.getMeasuredHeight() + mlp.topMargin + mlp.bottomMargin;


            }
        }


        height = Math.max(height1, height2);
        width = Math.max(width1, width2);
        width = (widhtMode == MeasureSpec.EXACTLY ? widthSize : width);
        height = (heightMode == MeasureSpec.EXACTLY ? heightSize : height);
        setMeasuredDimension(width, height);


    }

    //给每个小屁孩安家
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int a = getChildCount();
        MarginLayoutParams mlp = null;
        for (int i = 0; i < a; i++) {

            View v = getChildAt(i);
            mlp = (MarginLayoutParams) v.getLayoutParams();
            int vl = 0, vt = 0, vr = 0, vb = 0;
            switch (i) {
                case 0:

                    vl = mlp.leftMargin;
                    vt = mlp.topMargin;

                    break;
                case 1:

                    vl = getWidth() - v.getMeasuredWidth() - mlp.leftMargin - mlp.rightMargin;//逆向思维 懂吗  艹
                    Log.v("fuqi","vl"+vl);
                    vt = mlp.topMargin;
                    break;
                case 2:
                    vl = mlp.leftMargin;
                    vt = getHeight() - mlp.bottomMargin - v.getMeasuredHeight();

                    break;

                case 3:
                    vl = getWidth() - v.getMeasuredWidth() - mlp.leftMargin - mlp.rightMargin;
                    vt = getHeight() - mlp.bottomMargin - v.getMeasuredHeight();
                    break;

            }


            vr = vl + v.getMeasuredWidth();
            vb = vt + v.getMeasuredHeight();

            v.layout(vl, vt, vr, vb);
        }
    }

}