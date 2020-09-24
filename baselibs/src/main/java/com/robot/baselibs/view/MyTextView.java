package com.robot.baselibs.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

public class MyTextView extends TextView {

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setGravity(getGravity() | Gravity.CENTER_VERTICAL);
        setIncludeFontPadding(false);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint.FontMetrics fm = getPaint().getFontMetrics();
        if (fm != null) {
            if (getScrollY() != (int) (fm.ascent - fm.top)) {
                setScrollY((int) (fm.ascent - fm.top));
            }
        }

        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int padding = 0;
        Paint.FontMetrics fm = getPaint().getFontMetrics();
        if (fm != null) {
            padding = (int) Math.abs(fm.top - fm.ascent) + (int) Math.abs(fm.bottom - fm.descent);
            int width = getMeasuredWidth();
            int heiht = getMeasuredHeight();

            if (getText().toString().contains("g")
                    || getText().toString().contains("y")
                    || getText().toString().contains("p")) {
                setMeasuredDimension(width, heiht - padding);
            } else {
                setMeasuredDimension(width, heiht - padding - (int) (getTextSize() * 0.1));
            }
        }
    }
}