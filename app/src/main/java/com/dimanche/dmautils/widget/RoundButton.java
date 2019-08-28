package com.dimanche.dmautils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatButton;

import com.dimanche.dmautils.R;

public class RoundButton extends AppCompatButton {
    Context mContext;

    //背景颜色
    int backColor;
    //控件高度
    int height;
    //控件宽度
    int width;
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

    private void setShape(int mBackColor) {
        if (perfectCircle) {
            if (width > height) {
                radius = height / 2;
            } else {
                radius = width / 2;
            }
        }
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
        perfectCircle = roundTypedArray.getBoolean(R.styleable.Round_perfect_circle, false);
        roundTypedArray.recycle();
        TypedArray roundButtonTypedArray = mContext.obtainStyledAttributes(attributeSet, R.styleable.RoundButton);
        pressEffect = roundButtonTypedArray.getBoolean(R.styleable.RoundButton_press_effect, false);
        pressColor = roundButtonTypedArray.getColor(R.styleable.RoundButton_press_color, 0x00000000);
        roundButtonTypedArray.recycle();
        TypedArray androidTypedArray = mContext.obtainStyledAttributes(attributeSet, new int[]{
                android.R.attr.layout_width,
                android.R.attr.layout_height
        });
        width = (int) androidTypedArray.getDimension(0, 0f);
        height = (int) androidTypedArray.getDimension(1, 0f);
        androidTypedArray.recycle();

    }
}
