package com.robot.baselibs.view.banner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * Created by zero on 2017/9/30.
 * 轮播适配器
 */
public class VpAdapter<T> extends PagerAdapter {

    private List<T> datas = null;
    private IViewHolder mIViewHolder;
    private OnBannerItemClickListener<T> mItemClickListener;
    // 无限轮播的标记
    private boolean infinite;

    public List<T> getDatas() {
        return datas;
    }

    public int getDatasSize() {
        return datas == null ? 0 : datas.size();
    }


    public VpAdapter(List<T> list, IViewHolder<T> viewHolder, boolean i) {
        this.datas = list;
        this.mIViewHolder = viewHolder;
        this.infinite = i;
    }

    public VpAdapter setOnItemClickListener(OnBannerItemClickListener<T> mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
        return this;
    }

    @Override
    public int getCount() {
        int size;
        return datas == null || (size = datas.size()) == 0 ? 0 :
                infinite && size != 1 ?
                        Integer.MAX_VALUE >> 15 : size;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        VH<T> holder = mIViewHolder.create();
        View view = holder.createView(LayoutInflater.from(container.getContext()), container);
        container.addView(view);
        final int realPostion = getRealPostion(position);
        holder.onBind(view, datas.get(realPostion));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(v, datas.get(realPostion), realPostion, position);
                }
            }
        });

        return view;
    }

    /**
     * 获取该位置的正确的在集合中的位置
     *
     * @param position 显示位置
     * @return 集合中的位置
     */
    public int getRealPostion(int position) {
        int size;
        return (size = datas.size()) == 1 ? position :
                infinite && size != 0 ? position % size : position;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

}
