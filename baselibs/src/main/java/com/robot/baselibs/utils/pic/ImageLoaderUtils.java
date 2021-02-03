package com.robot.baselibs.utils.pic;

import android.content.Context;
import android.widget.ImageView;

import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.robot.baselibs.R;
import com.robot.baselibs.App;


/**
 * @Auther: kang
 * @Date: 2019-03-01 11:18
 * @Name: ImageLoaderUtils
 * @Description:
 */
public class ImageLoaderUtils {

    public static void displayLocalGif(int drawableId, ImageView imageView) {
        getGlideManager().asGif().load(drawableId).into(imageView);
    }


    public static void displayScale(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        RequestOptions options = new RequestOptions()
                .dontAnimate()
                .placeholder(R.color.white)
                .error(R.color.white)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        ImageLoaderUtils.getGlideManager().load(url).apply(options).into(imageView);
    }

    public static void display(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        RequestOptions options = getCommonGlideOption();
        getGlideManager().load(url).apply(options).into(imageView);
    }

    public static void displayCenterInside(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        RequestOptions options = getCenterInsideGlideOption();
        getGlideManager().load(url).apply(options).into(imageView);
    }

    public static void displayFitCenter(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .placeholder(R.drawable.ic_loading_placeholder)
                .error(R.drawable.ic_fail_placeholder)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        getGlideManager().load(url).apply(options).into(imageView);
    }

    public static void display(Context context, ImageView imageView, String url, RequestOptions options) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        getGlideManager().load(url).apply(options).into(imageView);
    }

    public static void displaySmallPhoto(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        RequestOptions options = getCommonGlideOption();
        getGlideManager().asBitmap().load(url).apply(options).thumbnail(0.5f).into(imageView);
    }

    public static void displayBigPhoto(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .dontAnimate()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .placeholder(R.drawable.ic_loading_placeholder)
                .error(R.drawable.ic_fail_placeholder)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        getGlideManager().asBitmap().load(url).apply(options).into(imageView);
    }

    public static void display(Context context, ImageView imageView, int url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        RequestOptions options = getCommonGlideOption();
        getGlideManager().load(url).apply(options).into(imageView);
    }

    public static void displayCenterInside(Context context, ImageView imageView, int url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        RequestOptions options = getCenterInsideGlideOption();
        getGlideManager().load(url).apply(options).into(imageView);
    }

    public static void displayCircle(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        RequestOptions options = new RequestOptions()
                .centerCrop().dontAnimate()
                .placeholder(R.drawable.ic_loading_placeholder)
                .error(R.drawable.ic_fail_placeholder)
                .transform(new GlideCircleTransfromUtil(context))
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        getGlideManager().load(url).apply(options).into(imageView);
    }

    /**
     * 加载圆形头像
     *
     * @param context
     * @param imageView
     * @param url
     */
    public static void displayCirclePortrait(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        RequestOptions options = new RequestOptions()
                .centerCrop().dontAnimate()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .transform(new GlideCircleTransfromUtil(context))
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        getGlideManager().load(url).apply(options).into(imageView);
    }

    /**
     * 设置界面加载圆形头像
     *
     * @param context
     * @param imageView
     * @param url
     */
    public static void displayCirclePortraitSetting(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        RequestOptions options = new RequestOptions()
                .centerCrop().dontAnimate()
                .placeholder(R.drawable.icon_normal_head)
                .error(R.drawable.icon_normal_head)
                .transform(new GlideCircleTransfromUtil(context))
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        getGlideManager().load(url).apply(options).into(imageView);
    }

    public static void displayRound(Context context, ImageView imageView, String url, int dp) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        getGlideManager().load(url).apply(getRoundCornerGlideOption(dp)).into(imageView);
    }

    public static void displayRoundWithSomeCorner(Context context, ImageView imageView, String url, int radius, boolean leftTop, boolean rightTop, boolean leftBottom, boolean rightBottom) {
        CornerTransform transformation = new CornerTransform(context, radius);
        //只是绘制左上角和右上角圆角
        transformation.setExceptCorner(leftTop, rightTop, leftBottom, rightBottom);
        RequestOptions options = new RequestOptions()
                .centerCrop().dontAnimate()
                .placeholder(R.drawable.ic_loading_placeholder)
                .error(R.drawable.ic_fail_placeholder)
                .transform(transformation)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        getGlideManager().load(url).apply(options).into(imageView);

    }

    public static RequestOptions getRoundCornerGlideOption(int radius) {
        return new RequestOptions()
                .dontAnimate()
                .placeholder(R.drawable.ic_loading_placeholder)
                .error(R.drawable.ic_fail_placeholder)
                .apply(new RequestOptions()
                        .transforms(new CenterCrop(), new RoundedCorners(SizeUtils.dp2px(radius))
                        ))
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
    }

    public static RequestOptions getCommonGlideOption() {
        return new RequestOptions()
                .centerCrop()
//                .dontAnimate()//会导致gif不动，有问题的话再找办法
                .placeholder(R.drawable.ic_loading_placeholder)
                .error(R.drawable.ic_fail_placeholder)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
    }

    public static RequestOptions getCenterInsideGlideOption() {
        return new RequestOptions()
                .centerInside()
//                .dontAnimate()//会导致gif不动，有问题的话再找办法
                .placeholder(R.drawable.ic_loading_placeholder)
                .error(R.drawable.ic_fail_placeholder)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
    }

    public static RequestManager getGlideManager() {
        return Glide.with(App.getContext());
    }

}
