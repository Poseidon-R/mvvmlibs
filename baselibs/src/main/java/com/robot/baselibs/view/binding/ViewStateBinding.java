package com.robot.baselibs.view.binding;

import android.view.View;

import androidx.databinding.BindingAdapter;

public class ViewStateBinding {

    @BindingAdapter("android:isSelected")
    public static void isSelected(View view, boolean isSelected) {
        view.setSelected(isSelected);
    }

    @BindingAdapter("android:isGone")
    public static void isGone(View view, boolean isGone) {
        view.setVisibility(isGone ? View.GONE : View.VISIBLE);
    }
}
