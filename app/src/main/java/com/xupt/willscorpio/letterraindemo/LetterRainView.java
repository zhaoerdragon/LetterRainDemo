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
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;

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
    @ColorInt
    private int mBackgroundColor;               //背景颜色

    private Paint mPaint;

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
        mLetterSize = 7;
        mSpeed = 2;

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(mLetterSize);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Toast.makeText(mContext, "宽度是:" + getWidth() + " 高度是:" + getHeight(), Toast.LENGTH_LONG).show();
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
            canvas.drawText(mValue, mCurrentX, mCurrentY, mPaint);
            mCurrentY = mCurrentY + mSpeed;
        }

    }
}
