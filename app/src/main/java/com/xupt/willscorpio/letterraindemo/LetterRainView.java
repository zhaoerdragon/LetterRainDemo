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

    private Paint mTextPaint;                   //文本画笔
    private Paint mBackgroundPaint;             //背景画笔

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

        mTextPaint = new Paint();
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextSize(mLetterSize);

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
            canvas.drawText(mValue, mCurrentX, mCurrentY, mTextPaint);
            mCurrentY = mCurrentY + mSpeed;
            canvas.drawText(mValue, mCurrentX, mCurrentY, mTextPaint);
        }

    }
}
