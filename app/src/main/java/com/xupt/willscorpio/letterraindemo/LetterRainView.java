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

    private List<LetterDrop> mLetterDropList = new ArrayList<>();           //存储雨滴的集合类

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


    // TODO: 2018/8/22 完成了基础的实现，但还又很多地方需要优化
    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;

        mLetterColor = Color.GREEN;
        mBackgroundColor = Color.BLACK;
        mLetterSize = 36;
        mSpeed = 10;
        mSize = 10;

        mTextPaint = new Paint();
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextSize(mLetterSize);
        mTextPaint.setColor(mLetterColor);
        mTextPaint.setAntiAlias(true);

        initData();
    }

    private void initData() {
        for (int i = 0; i < mSize; i++) {
            LetterDrop letterDrop = new LetterDrop("123", 20 + i * 30, 40 + i * 30);
            mLetterDropList.add(letterDrop);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(1000, 1000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("hahaha", "这个在调用吗");
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
            if (mCurrentY > getHeight()) {
                mCurrentY = 0;
            }
            canvas.drawText(mValue, mCurrentX, mCurrentY, mTextPaint);
            mCurrentY = mCurrentY + mSpeed;
            canvas.drawText(mValue, mCurrentX, mCurrentY, mTextPaint);
        }

    }
}
