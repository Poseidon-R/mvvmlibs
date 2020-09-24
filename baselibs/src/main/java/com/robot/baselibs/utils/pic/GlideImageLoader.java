package com.robot.baselibs.utils.pic;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Administrator on 2016/12/22.
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //Glide 加载图片简单用法
        RequestOptions options = new RequestOptions()
                .centerCrop()
//                .error(R.drawable.iv_empty)ø
//                .placeholder(R.drawable.iv_image_loading)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        ImageLoaderUtils.getGlideManager().load(path).apply(options).into(imageView);
    }
}
