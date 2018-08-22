package com.xupt.willscorpio.letterraindemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.renderscript.Sampler;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

/**
 * 字母雨控件
 */
public class LetterRainView extends View {

    private Context mContext;

    private int mSpeed;                         //速度

    @ColorInt
    private int mLetterColor;                   //字母颜色
    private String[] mValue;                    //下的内容
    private int mLetterSize;                    //字体大小
    private int mSize;                        //字母雨的数量

    @ColorInt
    private int mBackgroundColor;               //背景颜色

    private Paint mTextPaint;                   //文本画笔
    private Paint mBackgroundPaint;             //背景画笔

    private Boolean mInitSign = true;

    private List<LetterDrop> mLetterDropList = new ArrayList<>();           //存储雨滴的集合类

    private Random mRandom;                     //产生随机数

    public LetterRainView(Context context) {
        this(context, null);
    }

    public LetterRainView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterRainView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context, attrs, defStyleAttr);
    }


    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;

        mLetterColor = Color.GREEN;
        mBackgroundColor = Color.BLACK;
        mLetterSize = 36;
        mSpeed = 5;
        mSize = 30;

        mTextPaint = new Paint();
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextSize(mLetterSize);
        mTextPaint.setColor(mLetterColor);
        mTextPaint.setAntiAlias(true);

        mRandom = new Random();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = 100;
        int height = 100;
        switch (MeasureSpec.getMode(widthMeasureSpec)) {
            case MeasureSpec.EXACTLY:{
                width = MeasureSpec.getSize(widthMeasureSpec);
                break;
            }
            case MeasureSpec.AT_MOST:{
                width = MeasureSpec.getSize(widthMeasureSpec);
                break;
            }
            case MeasureSpec.UNSPECIFIED:{
                break;
            }
        }

        switch (MeasureSpec.getMode(heightMeasureSpec)) {
            case MeasureSpec.EXACTLY:{
                height = MeasureSpec.getSize(heightMeasureSpec);
                break;
            }
            case MeasureSpec.AT_MOST:{
                height = MeasureSpec.getSize(heightMeasureSpec);
                break;
            }
            case MeasureSpec.UNSPECIFIED:{
                break;
            }
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        initData();
    }

    private void initData() {
        for (int i = 0; i < mSize; i++) {
            int width = mRandom.nextInt(getWidth() - getTextWidth(mTextPaint, "黑客帝国"));
            int height = mRandom.nextInt(getHeight());
            LetterDrop letterDrop = new LetterDrop("黑客帝国", width, height);
            mLetterDropList.add(letterDrop);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mLetterDropList.size(); i++) {
            mLetterDropList.get(i).drawLetterDrop(canvas);
        }

        invalidate();
    }


    /**
     * 字母雨滴
     */
    class LetterDrop {

        String mValue;
        int mCurrentX;
        int mCurrentY;

        public LetterDrop(String value, int currentX, int currentY) {
            this.mValue = value;
            this.mCurrentX = currentX;
            this.mCurrentY = currentY;
        }

        public void drawLetterDrop(Canvas canvas) {
            if (mCurrentY > getHeight() + mLetterSize) {
                mCurrentY = 0;
                mCurrentX = mRandom.nextInt(getWidth());
            }
            canvas.drawText(mValue, mCurrentX, mCurrentY, mTextPaint);
            mCurrentY = mCurrentY + mSpeed;
        }

    }

    /**
     * @param paint
     * @param str
     * @return
     */
    public static int getTextWidth(Paint paint, String str) {
        int iRet = 0;
        if (str != null && str.length() > 0) {
            int len = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            for (int j = 0; j < len; j++) {
                iRet += (int) Math.ceil(widths[j]);
            }
        }
        return iRet;
    }

}
