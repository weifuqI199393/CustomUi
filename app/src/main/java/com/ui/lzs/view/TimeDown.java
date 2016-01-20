package com.ui.lzs.view;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import com.ui.lzs.test.R;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by lzs on 2015/11/18.
 *
 * @author cookie
 *         倒计时的一个view，灵活；
 */
public class TimeDown extends View {
    private Rect mRect;
    private Paint mPaint, mTextPaint;
    private int textSize;
    private int textColor;
    private String text;
    private int vWidth, vHeight, twidth, theight;
    private int recLen = 0;
    private int time = 0;
    private Timer timer;
    private TimerTask task;
    private Paint.FontMetrics fm;
    private boolean isAuto = false;

    public TimeDown(Context context) {
        super(context);
    }

    public TimeDown(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * get set 方法
     *
     * @return
     */
    public boolean isAuto() {
        return isAuto;
    }

    public void setIsAuto(boolean isAuto) {
        this.isAuto = isAuto;
        if (isAuto)
            starCountDown();
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;

    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;

    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
        this.recLen=time;
    }

    public TimeDown(Context context, AttributeSet attrs) {
        super(context, attrs);

        //获取自定义的属性
        TypedArray b = context.obtainStyledAttributes(attrs, R.styleable.first);
        for (int i = 0; i < b.getIndexCount(); i++) {
            int c = b.getIndex(i);

            switch (c) {
                case R.styleable.first_text:
                    text = b.getString(c);
                    break;
                case R.styleable.first_size:
                    textSize = (int) b.getDimension(R.styleable.first_size, 60);
                    break;
                case R.styleable.first_color:
                    textColor = b.getColor(c, Color.BLACK);
                    break;
                case R.styleable.first_time:
                    recLen=  time  = b.getInteger(c, 0);
                    break;
                case R.styleable.first_isAuto:
                    isAuto = b.getBoolean(c, false);

                    break;
            }

            if (isAuto) {
                starCountDown();
            }
        }
       b.recycle();
    }

    //测量大小

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wmode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int hmode = MeasureSpec.getMode(heightMeasureSpec);
        int heith = MeasureSpec.getSize(heightMeasureSpec);
        mTextPaint = new Paint();
        mTextPaint.setTextSize(textSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        fm = mTextPaint.getFontMetrics();
        twidth = (int) mTextPaint.measureText(text);
        theight = (int) Math.ceil(fm.descent - fm.ascent);
        if (wmode == MeasureSpec.EXACTLY) {
            vWidth = width;
        } else {
            vWidth = twidth + getPaddingLeft() + getPaddingRight();
        }
        if (hmode == MeasureSpec.EXACTLY) {
            vHeight = heith;
        } else {
            vHeight = theight + getPaddingTop() + getPaddingBottom();
        }
        setMeasuredDimension(vWidth, vHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        twidth = (int) mTextPaint.measureText(text);
        theight = (int) Math.ceil(fm.descent - fm.ascent);
        mPaint = new Paint();
        mPaint.setColor(Color.TRANSPARENT);
        canvas.drawRect(0, 0, vWidth, vHeight, mPaint);
        mPaint.setColor(textColor);
        mPaint.setTextSize(textSize);
        canvas.drawText(text, vWidth / 2 - twidth / 2, vHeight / 2 - fm.descent + theight / 2, mPaint);//文字显示高度以baseline为主

    }

    //倒计时处理器
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:

                    if (recLen <= 0) {
                        stopCountDown();

                    } else {
                        text ="("+recLen + ")";;
                        invalidate();
                    }
            }
        }
    };


    //开始倒计时
    public void starCountDown() {
        recLen = time;       // timeTask
        text = "("+recLen + ")";
        if (timer == null) {
            timer = new Timer();

        }
        if (task == null) {
            task = new TimerTask() {
                @Override
                public void run() {
                    recLen = recLen - 1;
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);

                }

            };
        }
        if (timer != null && task != null) {
            timer.schedule(task, 2000, 1000);// timeTask,0表示无延迟，1000，表示每隔1000毫秒执行一次
        }
    }

    //停止倒计时
    public void stopCountDown() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (task != null) {
            task.cancel();
            task = null;
        }
        setIsAuto(false);
        setText("开始");
        requestLayout();
        invalidate();
    }
}
