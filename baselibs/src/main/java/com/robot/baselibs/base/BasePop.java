package com.robot.baselibs.base;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;


/**
 * Created by apple on 2018/11/28.
 */

public abstract class BasePop extends PopupWindow {
    public Context mContext;

    public BasePop(Context context, boolean dim, boolean full) {
        super(context);
        mContext = context;
        final View mMenuView = initView(context);
        this.setContentView(mMenuView);
        if (full) {
            this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        } else {
            // 设置SelectPicPopupWindow弹出窗体的宽
            this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
//        this.setAnimationStyle(R.style.presentActivityAnimation);
        if (dim) {
            // 实例化一个ColorDrawable颜色为半透明
            ColorDrawable dw = new ColorDrawable(0x88000000);
            // 设置SelectPicPopupWindow弹出窗体的背景
            this.setBackgroundDrawable(dw);
        } else {
            // 实例化一个ColorDrawable颜色为半透明
            ColorDrawable dw = new ColorDrawable(0x00000000);
            // 设置SelectPicPopupWindow弹出窗体的背景
            this.setBackgroundDrawable(dw);
        }
        /** 防止底部虚拟按键遮挡popup  */
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

    }

    public abstract View initView(Context c);

}
