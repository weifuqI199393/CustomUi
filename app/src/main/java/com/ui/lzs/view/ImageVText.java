package com.ui.lzs.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ui.lzs.test.R;


/**
 * Created by lzs on 2015/12/10.
 */
public class ImageVText extends View{

   private  String text;
   private  int color,size,image ,imageStyle;
   private  int twidth,theight,iwidth,iheight,width,height,tiwidth,tiheight ;
   private Paint tpaint,paint;
    private Bitmap bit;
private    Paint.FontMetrics pf;
    public ImageVText(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a=context.obtainStyledAttributes(attrs, R.styleable.second);
        for (int i=0;i<a.length();i++){
            int b=a.getIndex(i);
            switch (b){

                case R.styleable.second_text:
                    text=a.getString(b);
                    break;
                case R.styleable.second_size:
                    size= (int) a.getDimension(b,14);
                    break;
                case R.styleable.second_color:
                    color=a.getColor(b,R.color.black_main_txt);
                    break;
                case R.styleable.second_image:
                    image=a.getResourceId(b, 0);
                    bit= BitmapFactory.decodeResource(getResources(), image);
                    break;
                case R.styleable.second_imageStyle:
                    imageStyle=a.getInt(b, 0);
                    break;


            }
        }
        a.recycle();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

             int specModew=MeasureSpec.getMode(widthMeasureSpec);
             int specModeh=MeasureSpec.getMode(heightMeasureSpec);
             int swidth=MeasureSpec.getSize(widthMeasureSpec);
             int sheight=MeasureSpec.getSize(heightMeasureSpec);
             tpaint=new Paint() ;tpaint.setTextSize(size);

            if (specModew==MeasureSpec.EXACTLY){
                width=swidth;
            }else{
                tiwidth=Math.max((int) tpaint.measureText(text),bit.getWidth());

                twidth= (int) tpaint.measureText(text)+getPaddingLeft()+getPaddingRight();
                iwidth=bit.getWidth()+getPaddingLeft()+getPaddingRight();
                    width=Math.max(twidth,iwidth);

            }
            if (specModeh==MeasureSpec.EXACTLY){
                height=sheight;
            }else{
                   pf=tpaint.getFontMetrics();
                height= (int) Math.ceil(pf.descent-pf.ascent)+bit.getHeight()+getPaddingTop()+getPaddingBottom();
                Log.v("abc","t"+(pf.descent-pf.ascent)+"i"+bit.getHeight()+"ti"+height);

            }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画矩形框

        paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0,0,width,height,paint);

        //画图片和文字
        paint=new Paint();
        canvas.drawBitmap(bit, width / 2 - bit.getWidth() / 2, height / 2 + (bit.getHeight() - pf.ascent) / 2 - bit.getHeight() + 5, paint);
        paint.setTextSize(size);
        paint.setColor(color);
        //如果text的长度大于控件长度，就显示省略号
        if (paint.measureText(text) > width) {
            TextPaint paint = new TextPaint(tpaint);
            String msg = TextUtils.ellipsize(text, paint, (float) width - getPaddingLeft() - getPaddingRight(),
                    TextUtils.TruncateAt.END).toString();
        }
        canvas.drawText(text,width/2-tiwidth/2,height/2-(bit.getHeight()-pf.ascent)/2-pf.ascent-5,paint);

    }


}
