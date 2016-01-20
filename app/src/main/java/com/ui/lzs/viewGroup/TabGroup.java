package com.ui.lzs.viewGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.ui.lzs.viewAdapter.TabGroupAdapter;
import com.ui.lzs.viewEvent.ObserverBase;

import java.util.ArrayList;
import java.util.List;

/**
 * textview文字未居中
 * Created by lzs on 2015/12/21.
 */
public class TabGroup extends ViewGroup {

    private List<List<View>> vLists = new ArrayList<List<View>>();
    private List<View> vList = new ArrayList<View>();
    private List<Integer> hList = new ArrayList<Integer>();
    private WindowManager wm;
    private Animation an;
    private TabGroupAdapter<String[]> mTabGroupAdapter;

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    public TabGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

    }

    /**
     * 最长的lineWidth，总的height，每个height，
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int lineWidth = 0;
        int lineHeight = 0;
        int width = 0;
        int height = 0;
        int count = getChildCount();
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        vLists.clear();
        vList.clear();
        for (int i = 0; i < count; i++) {
            View v = getChildAt(i);
            measureChild(v, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams mp = (MarginLayoutParams) v.getLayoutParams();
            int childWidth = v.getMeasuredWidth() + mp.leftMargin + mp.rightMargin;
            int childHeight = v.getMeasuredHeight() + mp.topMargin + mp.bottomMargin;

            if ((lineWidth + childWidth > widthSize)||(lineWidth + childWidth > wm.getDefaultDisplay().getWidth())) {
                width = Math.max(lineWidth, childWidth);
                lineWidth = childWidth;
                height += lineHeight;
                lineHeight = childHeight;
                vLists.add(vList);
                hList.add(height);
                vList = new ArrayList<View>();
                vList.add(v);
            } else {
                vList.add(v);
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }
            if (i == count - 1) {
                vLists.add(vList);
                width = Math.max(lineWidth, width);
                height += lineHeight;

            }


        }

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            if (width > wm.getDefaultDisplay().getWidth()) {
                width = wm.getDefaultDisplay().getWidth();
            }
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            if (height > wm.getDefaultDisplay().getHeight()) {
                height = wm.getDefaultDisplay().getHeight();
            }
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int vl = 0, vr = 0, vb = 0, height = 0, vt = 0, m = 0;
        for (int i = 0; i < vLists.size(); i++) {
            height = hList.get(i);
            vList = vLists.get(i);
            for (int j = 0; j < vList.size(); j++) {
                m++;
                View v = vList.get(j);
                MarginLayoutParams mp = (MarginLayoutParams) v.getLayoutParams();
                vl = vr + mp.leftMargin;
                if (i == 0) {
                    vt = mp.topMargin;
                }
                vr = vl + v.getMeasuredWidth() + mp.rightMargin;
                vb = vt + v.getMeasuredHeight() + mp.bottomMargin;
                //动画设置
                an = new AlphaAnimation(0.0f, 1.0f);
                an.setDuration(300 * m);
                an.setFillBefore(false);
                v.startAnimation(an);
                v.layout(vl, vt, vr, vb);
                if (j == vList.size() - 1) {
                    vl = 0;
                    vr = 0;
                    vt = height + mp.topMargin;
                }
            }


        }

    }
    //设置数据
    public void setAadapter(TabGroupAdapter<String[]> mTabGroupAdapter){
        this.mTabGroupAdapter=mTabGroupAdapter;
        removeAllViews();
        for (int i=0;i<mTabGroupAdapter.getCount();i++){
            View v=mTabGroupAdapter.getview(this,i,mTabGroupAdapter.getItem(i));
            v.setLayoutParams(v.getLayoutParams());
            addView(v);
        }
    }

    public class TabObserver extends ObserverBase{


        public void  onchange(){

        }

    }

}
