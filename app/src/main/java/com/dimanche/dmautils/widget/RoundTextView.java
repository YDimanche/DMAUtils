package com.dimanche.dmautils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatTextView;

import com.dimanche.dmautils.R;


public class RoundTextView extends AppCompatTextView {

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


    public RoundTextView(Context context) {
        super(context);
        mContext = context;
        init();
        setShape();
    }

    public RoundTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
        getTypeArr(attrs);
        setShape();
    }

    public RoundTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
        getTypeArr(attrs);
        setShape();
    }

    /***
     * 设置填充颜色
     * @param backColor
     */
    public void setBackColor(int backColor) {
        this.backColor = backColor;
        setShape();

    }

    /**
     * 设置圆角
     *
     * @param radius
     */
    public void setRadius(int radius) {
        this.radius = radius;
        setShape();
    }

    /**
     * 设置边框宽度
     *
     * @param boderWidth
     */
    public void setBoderWidth(int boderWidth) {
        this.boderWidth = boderWidth;
        setShape();
    }

    /**
     * 设置边框颜色
     *
     * @param borderColor
     */
    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        setShape();
    }


    /**
     * 初始化默认的全局属性
     */
    private void init() {
        //设置内部文字居中
        setGravity(Gravity.CENTER);
    }

    /**
     * 设置圆角等
     */
    private void setShape() {

        GradientDrawable gradientDrawable = new GradientDrawable();
        //设置矩形
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        //设置描边
        gradientDrawable.setStroke(boderWidth, borderColor);
        //设置弧度
        gradientDrawable.setCornerRadius(radius);
        //设置填充颜色
        gradientDrawable.setColor(backColor);
        setBackground(gradientDrawable);
    }


    /**
     * 获取布局中xml定义的属性值
     */
    private void getTypeArr(AttributeSet attrs) {
        TypedArray myTypedArray = mContext.obtainStyledAttributes(attrs, R.styleable.Round);
        backColor = myTypedArray.getColor(R.styleable.Round_backColor, 0x00000000);
        radius = (int) myTypedArray.getDimension(R.styleable.Round_radius, 0f);
        boderWidth = (int) myTypedArray.getDimension(R.styleable.Round_border_width, 0f);
        borderColor = myTypedArray.getColor(R.styleable.Round_border_color, 0x00000000);
        myTypedArray.recycle();


    }


}
