package com.robot.baselibs.view.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;

import androidx.annotation.AttrRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.robot.baselibs.R;
import com.robot.baselibs.util.Logger;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.robot.baselibs.util.SizeUtils.dp2px;


/**
 * Created by zero on 2017/10/1.
 */

public class DkBanner<T> extends FrameLayout {

    private final int DEFAULT_SELECT = R.drawable.ic_carousel_pressed;
    private final int DEFAULT_NORAML = R.drawable.ic_carousel_normal;
    private final int DEFAULT_SPACE = 20;
    private final int DEFAULT_WIDTH = ViewGroup.LayoutParams.WRAP_CONTENT;
    private final int DEFAULT_HEIGHT = ViewGroup.LayoutParams.WRAP_CONTENT;
    private final boolean DEFAULT_CAN_LOOP = false;
    private final int DEFAULT_LOOP_TIME = 3000;

    // 节约时间  可以轮播的情况  一律视为无限轮播
    private boolean isInfinite = false;
    private int loopTime;
    private ViewPager mVp;
    private VpAdapter<T> mVpAdapter;
    private Observable<Long> longObservable;
    private Disposable subscribe;
    private LinearLayout mLl;
    private OnBannerItemClickListener<T> itemClickListener;
    // 指示点的间隔
    private int indicatorPointSpace = 20;
    // 指示器宽高
    private int indicatorPointWidth = ViewGroup.LayoutParams.WRAP_CONTENT, indicatorPointHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
    // 指示器的点图片
    private int select, noraml;

    public DkBanner<T> setOnItemClickListener(OnBannerItemClickListener<T> listener) {
        itemClickListener = listener;
        if (mVpAdapter != null) {
            mVpAdapter.setOnItemClickListener(itemClickListener);
        }
        return this;
    }

    public DkBanner(@NonNull Context context) {
        this(context, null, 0);
    }

    public DkBanner(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DkBanner(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs, defStyleAttr);
    }

    private void initialize(Context c, AttributeSet a, int d) {
        TypedArray arrs = c.obtainStyledAttributes(a, R.styleable.DkBannerView, d, 0);

        loopTime = arrs.getInt(R.styleable.DkBannerView_loop_time, DEFAULT_LOOP_TIME);
        select = arrs.getResourceId(R.styleable.DkBannerView_select_point, DEFAULT_SELECT);
        noraml = arrs.getResourceId(R.styleable.DkBannerView_noraml_point, DEFAULT_NORAML);
        indicatorPointWidth = arrs.getInteger(R.styleable.DkBannerView_point_width, DEFAULT_WIDTH);
        indicatorPointHeight = arrs.getInteger(R.styleable.DkBannerView_point_height, DEFAULT_HEIGHT);
        indicatorPointSpace = arrs.getInteger(R.styleable.DkBannerView_point_space, DEFAULT_SPACE);

        arrs.recycle();
        mVp = new ViewPager(c);
        LayoutParams lps = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mVp.setClipToPadding(false);
        addView(mVp, lps);
        mVp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // super.onPageSelected(position);
                updateIndicator(position);
            }
        });
        mLl = new LinearLayout(getContext());
        mLl.setOrientation(LinearLayout.HORIZONTAL);
        lps = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lps.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        // lps.bottomMargin = AutoUtils.getPercentHeightSize(26);
        addView(mLl, lps);
        initViewPagerScroll();
    }

    public DkBanner<T> setVpAdapter(List<T> list, IViewHolder<T> viewHolder) {
        return setVpAdapter(list, viewHolder, isInfinite);
    }

    public DkBanner<T> setVpAdapter(List<T> list, IViewHolder<T> viewHolder, boolean infinite) {
        mVpAdapter = new VpAdapter<>(list, viewHolder, infinite);
        mVp.setAdapter(mVpAdapter);
        if (itemClickListener != null) {
            mVpAdapter.setOnItemClickListener(itemClickListener);
        }
        checkAutoLooper();

        setIndicator();
        return this;
    }

    private DkBanner<T> checkAutoLooper() {
        int datasSize = mVpAdapter.getDatasSize();
        if (datasSize == 0) {
            return this;
        }
        if (datasSize == 1) {
            mVp.setCurrentItem(0);
            stopLoop();
            return this;
        }
        int count = mVpAdapter.getCount();
        if (count != datasSize) {
            int offSet = count / 2 - count / 2 % datasSize;
            mVp.setCurrentItem(offSet);
            starLoop();
        }
        return this;
    }

    /**
     * 停止轮播
     */
    public void stopLoop() {
        if (subscribe != null && !subscribe.isDisposed()) {
            subscribe.dispose();
            subscribe = null;
        }
    }

    /**
     * 开始轮播
     */
    public void starLoop() {
        if (longObservable == null) {
            createObservable();
        }
        if (subscribe == null) {
            subscribe = longObservable.subscribe(new Consumer<Long>() {
                @Override
                public void accept(Long aLong) throws Exception {
                    int currentItem = mVp.getCurrentItem();
                    mVp.setCurrentItem(++currentItem);
                }
            });
        }
    }

    private void createObservable() {
        longObservable = Observable.interval(loopTime, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (MotionEvent.ACTION_DOWN == ev.getAction()) {
            stopLoop();
        } else if (MotionEvent.ACTION_UP == ev.getAction()) {
            starLoop();
        }
        return super.dispatchTouchEvent(ev);
    }


    /**
     * 设置ViewPager的滑动速度
     */
    private void initViewPagerScroll() {
        try {
            Field mScroller = null;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            Scroller scroller = new ViewPagerScroller(
                    mVp.getContext());
            mScroller.set(mVp, scroller);
        } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public void notifyDataSetChanged() {
        mVpAdapter.notifyDataSetChanged();
        checkAutoLooper();
        setIndicator();
    }


    /**
     * 设置指示器
     */
    public void setIndicator() {
        if (mVpAdapter == null) {
            return;
        }
        if (mVpAdapter.getDatasSize() <= 1) {
            return;
        }
        int realCount = mVpAdapter.getDatasSize();
        int realPostion = mVpAdapter.getRealPostion(mVp.getCurrentItem());
        mLl.removeAllViews();
        LinearLayout.LayoutParams lps = new LinearLayout.LayoutParams(indicatorPointWidth, indicatorPointHeight);
        for (int i = 0; i < realCount; i++) {
            ImageView view = new ImageView(getContext());
            if (i != 0) {
                lps.leftMargin = indicatorPointSpace;
            } else {
                lps.leftMargin = 0;
            }
            lps.bottomMargin = dp2px(getContext(),13);
            view.setLayoutParams(lps);
            view.setImageResource(i == realPostion ? select : noraml);
            mLl.addView(view);
        }



        Logger.e("TAG", "mLl.size -> " + mLl.getChildCount());
    }

    public void updateIndicator(int postion) {
        int childCount = mLl.getChildCount();
        postion = mVpAdapter.getRealPostion(postion);
        for (int i = 0; i < childCount; i++) {
            ImageView childAt = (ImageView) mLl.getChildAt(i);
            childAt.setImageResource(i == postion ? select : noraml);
        }
    }

    public void setCurrentItem(int i) {
        mVp.setCurrentItem(i);
    }

    /**
     * 设置轮播图指示器高度和位置
     *
     * @param lps
     */
    public void setIndicatorLayoutParams(LayoutParams lps) {
        mLl.setLayoutParams(lps);
    }

    public void setIndicatorPoint(@DrawableRes int select, @DrawableRes int noraml, int width, int height) {
        this.select = select;
        this.noraml = noraml;
        setIndicatorPoint(width, height);
    }

    public void setIndicatorPoint(int width, int height) {
        this.indicatorPointWidth = width;
        this.indicatorPointHeight = height;
        setIndicator();
    }


    public void setPageTransformer(ViewPager.PageTransformer transformer) {
        mVp.setPageTransformer(true, transformer);
    }

    public void setVpClipToPadding(int left, int top, int right, int bottom, boolean clipToPadding) {
        mVp.setPadding(left, top, right, bottom);
        mVp.setClipToPadding(clipToPadding);
    }

    public int getLoopTime() {
        return loopTime;
    }

    public DkBanner setLoopTime(int loopTime) {
        this.loopTime = loopTime;
        stopLoop();
        starLoop();
        return this;
    }

}
