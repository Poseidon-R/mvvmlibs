package com.robot.baselibs.view.banner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zero on 2017/9/27.
 */

public interface VH<T> {

    View createView(LayoutInflater inflater, ViewGroup container);

    void onBind(View view, T t);

}
