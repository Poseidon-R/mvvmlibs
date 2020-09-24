package com.robot.baselibs.view.banner;

import android.view.View;

/**
 * Created by zero on 2017/10/11.
 * 轮播单项点击器
 */
public interface OnBannerItemClickListener<T> {

    /**
     * @param view        点击的view
     * @param postion     在集合中的正确位置
     * @param realPostion 显示在viewPager的位置
     */
    void onItemClick(View view, T t, int postion, int realPostion);

}
