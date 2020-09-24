package com.robot.baselibs.view;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import me.goldze.mvvmhabit.BuildConfig;


/**
 * Created by wuqianrui on 2019/03/09 15:38
 * 当前页面调试工具
 */
public class DebugWatermarkText {
    public static void addDebugTextView(Activity activity) {
        if (BuildConfig.DEBUG) {
            //添加一个TextView,用来提示当前的Activity类
            View decorView = activity.getWindow().getDecorView();
            View rootView = activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT);
            final String tag = "addDebugTextView";
            View debugTextView = decorView.findViewWithTag(tag);
            if (debugTextView != null) {
                return;
            }
            if (decorView instanceof FrameLayout) {
                TextView textView = new TextView(activity);
                textView.setTag(tag);
                textView.setTextSize(9);
                textView.setTextColor(Color.WHITE);
                float dp2 = 2;
                int padding = (int) dp2 * 4;
                textView.setPadding(padding, padding, padding, padding);
                textView.setShadowLayer(dp2 * 2, dp2, dp2, Color.BLACK);

                textView.setText(activity.getClass().getSimpleName());

                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
                layoutParams.gravity = Gravity.BOTTOM;
                if (decorView.getBottom() > rootView.getBottom()) {
                    //显示了导航栏
                    Resources resources = activity.getResources();
                    int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
                    int navBarHeight = 0;
                    if (resourceId > 0) {
                        navBarHeight = resources.getDimensionPixelSize(resourceId);
                    }
                    layoutParams.bottomMargin = navBarHeight;
                }
                ((ViewGroup) decorView).addView(textView, layoutParams);
            }
        }
    }
}
