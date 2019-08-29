package com.dimanche.dmautils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatButton;

import com.dimanche.dmautils.R;

public class RoundButton extends AppCompatButton {
    Context mContext;

    //背景颜色
    int backColor;
    //圆角大小
    int radius;
    //边框宽度
    int boderWidth;
    //边框颜色
    int borderColor;
    //是否正圆
    boolean perfectCircle = false;
    //是否有按下效果
    boolean pressEffect;
    //按下时的颜色
    int pressColor;

    public RoundButton(Context context) {
        super(context);
        mContext = context;
        setShape(backColor);
    }

    public RoundButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        getTypeArr(attrs);
        setShape(backColor);
    }

    public RoundButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        getTypeArr(attrs);
        setShape(backColor);

    }

    /***
     * 设置填充颜色
     * @param backColor
     */
    public void setBackColor(int backColor) {
        this.backColor = backColor;
        setShape(backColor);

    }

    /**
     * 设置圆角
     *
     * @param radius
     */
    public void setRadius(int radius) {
        this.radius = radius;
        setShape(backColor);
    }

    /**
     * 设置边框宽度
     *
     * @param boderWidth
     */
    public void setBoderWidth(int boderWidth) {
        this.boderWidth = boderWidth;
        setShape(backColor);
    }

    /**
     * 设置边框颜色
     *
     * @param borderColor
     */
    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        setShape(backColor);
    }


    /**
     * 是否有按下效果
     *
     * @param pressEffect 是否有按下效果
     * @param pressColor  如果为false，该参数传0即可
     */
    public void setPressEffect(boolean pressEffect, int pressColor) {
        this.pressEffect = pressEffect;
        if (pressEffect) {
            this.pressColor = pressColor;
            setShape(pressColor);
        } else {
            setShape(backColor);
        }

    }

    private void setShape(int mBackColor) {

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setStroke(boderWidth, borderColor);
        gradientDrawable.setColor(mBackColor);
        gradientDrawable.setCornerRadius(radius);
        setBackground(gradientDrawable);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (pressEffect) {
                    setShape(pressColor);
                }
                break;

            case MotionEvent.ACTION_CANCEL:
                if (pressEffect) {
                    setShape(backColor);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (pressEffect) {
                    setShape(backColor);
                }
                break;
            default:
                break;

        }
        return true;

    }

    /**
     * 获取xml布局中定义的属性
     */
    private void getTypeArr(AttributeSet attributeSet) {
        TypedArray roundTypedArray = mContext.obtainStyledAttributes(attributeSet, R.styleable.Round);
        backColor = roundTypedArray.getColor(R.styleable.Round_backColor, 0x00000000);
        radius = (int) roundTypedArray.getDimension(R.styleable.Round_radius, 0f);
        boderWidth = (int) roundTypedArray.getDimension(R.styleable.Round_border_width, 0f);
        borderColor = roundTypedArray.getColor(R.styleable.Round_border_color, 0x00000000);
        roundTypedArray.recycle();
        TypedArray roundButtonTypedArray = mContext.obtainStyledAttributes(attributeSet, R.styleable.RoundButton);
        pressEffect = roundButtonTypedArray.getBoolean(R.styleable.RoundButton_press_effect, false);
        pressColor = roundButtonTypedArray.getColor(R.styleable.RoundButton_press_color, 0x00000000);
        roundButtonTypedArray.recycle();


    }
}
