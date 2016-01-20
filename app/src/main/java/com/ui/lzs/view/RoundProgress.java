package com.ui.lzs.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.ui.lzs.test.R;


/**
 * Created by lzs on 2015/12/14.
 */
public class RoundProgress extends View {

    private int tcolor,ncolor,zcolor,tsize,radiusWidth,radius,progressMax,progressIng,progressdq=0;
    private String text;
    private Paint tpaint,rpaint;
    private float speed;
    private boolean next=false;
    public RoundProgress(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a=context.obtainStyledAttributes(attrs, R.styleable.third);
        for (int i=0;i<a.length();i++){
            int b=a.getIndex(i);
            switch (b){
                case R.styleable.third_color:
                    tcolor=a.getColor(R.styleable.third_color, Color.BLACK);
                    break;
                case R.styleable.third_colorn:
                    ncolor=a.getColor(R.styleable.third_colorn, Color.BLACK);
                    break;
                case R.styleable.third_colorz:
                    zcolor=a.getColor(R.styleable.third_colorz, Color.BLACK);
                    break;
                case R.styleable.third_text:
                    text=a.getString(R.styleable.third_text);
                    break;
                case R.styleable.third_size:
                     tsize= (int) a.getDimension(R.styleable.third_size, 0);
                    break;
                case R.styleable.third_radiuswidth:
                    radiusWidth= (int) a.getDimension(R.styleable.third_radiuswidth, 0);
                    break;
                case R.styleable.third_progressing:
                    progressIng=a.getInt(R.styleable.third_progressing,0);
                    break;
                case R.styleable.third_progressmax:
                    progressMax=a.getInt(R.styleable.third_progressmax,0);
                    break;

            }


        }
        new Thread()
        {
            public void run()
            {
                while (true)
                {


                    if (next) {
                        progressIng=progressIng+1;
                        if (progressIng == 360)
                        {
                            progressIng = 0;
                        }
                        Log.v("abc","progressIng1:"+progressIng);
                        postInvalidate();
                        next=false;
                    }
                    try
                    {
                       Thread.sleep(0);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            };
        }.start();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        //画空心圆
        rpaint=new Paint();
        rpaint.setColor(ncolor);
        rpaint.setStrokeWidth(radiusWidth);//描边的宽度
        rpaint.setStyle(Paint.Style.STROKE);//空心
       // rpaint.setAntiAlias(true);//消除锯齿
        canvas.drawCircle(getWidth()/2, getWidth()/2, getWidth()/2-radiusWidth/2, rpaint);
        //画text
        tpaint=new Paint();
        tpaint.setTextSize(tsize);
        tpaint.setColor(tcolor);
        tpaint.setAntiAlias(true);//消除锯齿
        int width= (int) tpaint.measureText(((int)((progressIng/3.6)))+"%");
        Paint.FontMetrics fm=tpaint.getFontMetrics();
        int heigth = (int) Math.ceil(fm.descent-fm.ascent);
        canvas.drawText(((int)((progressIng/3.6)))+"%",getWidth()/2-width/2,getHeight()/2+heigth/2-fm.descent,tpaint);
        //画圆弧
        rpaint.setColor(zcolor);
        RectF rf=new RectF(radiusWidth/2,radiusWidth/2,getWidth()-radiusWidth/2,getWidth()-radiusWidth/2);
        for (int i=progressdq;i<=progressIng;i++) {
             int j=-90+progressdq;
            if (j<=60) {
                canvas.drawArc(rf, j + i, i, false, rpaint);
                progressdq++;

                Log.v("abc", "progressdq2:" + progressdq);
                if (i == progressIng || i == 60) {
                    next = true;
                    break;
                }
            }
        }

    }






}
