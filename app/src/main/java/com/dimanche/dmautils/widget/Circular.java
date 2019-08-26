package com.dimanche.dmautils.widget;

import java.util.Random;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author Dimanche 2019.8.8 圆角Textview
 */
public class Circular extends RelativeLayout {
    Context mContext;
    TextView textView;
    int color = 0;
    int height;
    int width;

    public Circular(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        initView(context);
    }

    public Circular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
        TypedArray a = context.obtainStyledAttributes(attrs,
                new int[]{android.R.attr.layout_width, android.R.attr.layout_height});
        width = (int) a.getDimension(0, 0f); //第一个参数表示在int数组中的索引，第二个参数为默认值
        height = (int) a.getDimension(1, 0f); //第一个参数表示在int数组中的索引，第二个参数为默认值
        initView(context);


    }

    public Circular(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        TypedArray a = context.obtainStyledAttributes(attrs,
                new int[]{android.R.attr.layout_width, android.R.attr.layout_height});
        width = (int) a.getDimension(0, 0f); //第一个参数表示在int数组中的索引，第二个参数为默认值
        height = (int) a.getDimension(1, 0f); //第一个参数表示在int数组中的索引，第二个参数为默认值

        initView(context);

    }

    private void initView(Context context) {
        mContext = context;
        textView = new TextView(mContext);
        textView.setText("电");
        textView.setTextColor(0xffffffff);
        textView.setBackgroundColor(0);

        LayoutParams param = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        param.addRule(CENTER_IN_PARENT);
        setRound(this);
        this.addView(textView, param);
    }

    /**
     * 圆角中的显示内容
     *
     * @param msg
     */
    public void setText(String msg) {
        if (msg == null) {
            msg = "电";
        } else if (msg.length() > 1) {
            msg = msg.substring(0, 1);
        }
        textView.setText(msg);
    }

    public void setTextSize(int size) {
        textView.setTextSize(size);
    }

    /**
     * 设置圆的背景色
     */
    public void setCirColor(int color) {
        this.color = color;
        setRound(this);
    }

    /**
     * 设置view圆角
     *
     * @param view
     */
    private void setRound(final View view) {
        int radius = 0;

        if (width > height) {
            radius = height / 2;
        } else {
            radius = width / 2;
        }
        int borderWidth = 0;// 设置边框宽度为0，不然将为空心圆
        float[] outerRadius = new float[8];
        float[] innerRadius = new float[8];
        for (int i = 0; i < 8; i++) {
            outerRadius[i] = radius + borderWidth;
            innerRadius[i] = radius;
        }
        ShapeDrawable shapeDrawable = new ShapeDrawable(
                new RoundRectShape(outerRadius, new RectF(borderWidth,
                        borderWidth, borderWidth, borderWidth),
                        innerRadius));
        shapeDrawable.getPaint().setColor(getColor());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            // 高版本SDK使用新的API
            view.setBackground(shapeDrawable);
        } else {
            view.setBackgroundDrawable(shapeDrawable);
        }


    }

    /**
     * 得到颜色
     *
     * @return
     */
    public int getColor() {
        if (color != 0) {
            return color;
        }
        // 生成随机颜色
        int[] color = {0xffFF8000, 0xff0080FF, 0xff458A00, 0xffFF0080,
                0xffFF0000, 0xffA8A439, 0xff5D1D08, 0xff331D82};
        int randomColor = getRandom(color.length);
        return color[randomColor];
    }

    /**
     * 获取一个和上一个不同的随机数
     */
    public static int lastColor = 0;

    private int getRandom(int length) {
        int randomColor = 0;

        Random random = new Random();

        while (true) {
            randomColor = random.nextInt(length);
            if (randomColor != lastColor) {
                lastColor = randomColor;
                break;
            }
        }
        return randomColor;
    }


}
