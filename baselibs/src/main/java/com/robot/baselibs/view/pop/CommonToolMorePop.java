package com.robot.baselibs.view.pop;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.robot.baselibs.R;
import com.robot.baselibs.base.BasePop;
import com.robot.baselibs.databinding.PopShopGoodMoreBinding;


/**
 * Created by apple on 2018/11/29.
 */

public class CommonToolMorePop extends BasePop {
    private OnMoreOptionClickListener mOnMoreOptionClickListener;

    private PopShopGoodMoreBinding binding;

    public CommonToolMorePop(Context context) {
        super(context, false, false);
        initListener();
    }

    @Override
    public View initView(Context c) {
        LayoutInflater layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mContentView = layoutInflater.inflate(R.layout.pop_shop_good_more, null);
        binding = DataBindingUtil.bind(mContentView);
        setContentView(mContentView);
//        setAnimationView(mContentView, 200);
        return mContentView;
    }

    @Override
    public void showAsDropDown(View anchor) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            setHeight(height);
            super.showAsDropDown(anchor);
        } else {
            super.showAsDropDown(anchor);
        }

//        if (mOpenAnimator != null) {
//            mOpenAnimator.start();
//        }
    }

    private void initListener() {
        binding.tvMoreMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnMoreOptionClickListener != null) {
                    mOnMoreOptionClickListener.onMoreOptionClick(0);
                }
            }
        });
        binding.tvMoreMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnMoreOptionClickListener != null) {
                    mOnMoreOptionClickListener.onMoreOptionClick(1);
                }
            }
        });
        binding.tvMoreSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnMoreOptionClickListener != null) {
                    mOnMoreOptionClickListener.onMoreOptionClick(2);
                }
            }
        });
        binding.tvMoreShopcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnMoreOptionClickListener != null) {
                    mOnMoreOptionClickListener.onMoreOptionClick(3);
                }
            }
        });
        binding.tvMoreMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnMoreOptionClickListener != null) {
                    mOnMoreOptionClickListener.onMoreOptionClick(4);
                }
            }
        });
    }


    public interface OnMoreOptionClickListener {
        void onMoreOptionClick(int pos);
    }

    public void setOnMoreOptionClickListener(OnMoreOptionClickListener listener) {
        this.mOnMoreOptionClickListener = listener;
    }
}
