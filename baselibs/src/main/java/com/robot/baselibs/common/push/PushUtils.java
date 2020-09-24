package com.robot.baselibs.common.push;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.blankj.utilcode.util.ViewUtils;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.robot.baselibs.BuildConfig;
import com.robot.baselibs.R;
import com.robot.baselibs.configs.constants.BizConstant;
import com.robot.baselibs.utils.json.JsonUtil;
import com.robot.baselibs.utils.pic.ImageLoaderUtils;
import com.umeng.message.entity.UMessage;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * notification展示
 */
public class PushUtils {
    public static void showNotification(Context context, UMessage msg) {
//        Intent targetIntent = new Intent(context, MainActivity.class);
//        targetIntent.putExtra(BizConstant.TARGET_INTENT_KEY, BizConstant.BROADCAST_OPTION_ONE);
//        prepareNotification(context, msg, targetIntent);
    }

    private static void prepareNotification(Context context, UMessage msg, Intent targetIntent) {
        int notificationId = msg.builder_id;
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, BizConstant.CHANNEL_FCJ_NOTIFICATION);
        HashMap hashMap = (HashMap) msg.extra;//category,skipContent,skipType,cover
        String cover = (String) hashMap.get("cover");
        JSONObject jsonObject = JsonUtil.getJsonObjectFromMap(hashMap);
        if (jsonObject != null) {
            targetIntent.putExtra(BizConstant.PUSH_INFO_KEY, jsonObject.toString());
        }
        mBuilder.setContentTitle(msg.title)//设置通知栏标题
                .setContentText(msg.text) //设置通知栏显示内容
                .setContentIntent(PendingIntent.getActivity(context, 1, targetIntent, PendingIntent.FLAG_UPDATE_CURRENT)) //设置通知栏点击意图
                //  .setNumber(number) //设置通知集合的数量
                .setTicker(msg.ticker)//通知首次出现在通知栏，带上升动画效果的
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                .setPriority(NotificationCompat.PRIORITY_DEFAULT) //设置该通知优先级
                .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                //.setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                //                .setSound(Uri.parse("android.resource://" + FireBirdApplication.applicationInstance().getPackageName() + "/" + R.raw.beep))
                .setSmallIcon(R.mipmap.ic_launcher);//注：PendingIntent开启新的界面，intent携带值传递的时候，PendingIntent的类型是FLAG_UPDATE_CURRENT，新的值过来，自动更新，intent需要设置Action才可以获得传递的值
        if (!TextUtils.isEmpty(cover)) {
            ViewUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    ImageLoaderUtils.getGlideManager().asBitmap().load(BuildConfig.PIC_BASE_URL + cover).into(new SimpleTarget<Bitmap>() {
//                        @Override
//                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                            mBuilder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(resource));
//                            //设置该通知优先级
//                            adapterAndroidOreo(mNotificationManager);
//                            mNotificationManager.notify(notificationId, mBuilder.build());
//                        }
//                    });
                }
            });
        } else {
            //设置该通知优先级
            adapterAndroidOreo(mNotificationManager);
            mNotificationManager.notify(notificationId, mBuilder.build());
        }
    }

    /**
     * 适配android8.0
     *
     * @param mNotificationManager
     */
    private static void adapterAndroidOreo(NotificationManager mNotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(BizConstant.CHANNEL_FCJ_NOTIFICATION, "方寸间", NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableVibration(true);
            //是否绕过请勿打扰模式
            channel.canBypassDnd();
            //锁屏显示通知
//            channel.setLockscreenVisibility(VISIBILITY_SECRET);
//            AudioAttributes audioAttributes = new AudioAttributes.Builder()
//                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
//                    .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
//                    .build();
//            channel.setSound(Uri.parse("android.resource://" + FireBirdApplication.applicationInstance().getPackageName() + "/" + R.raw.beep), audioAttributes);
            mNotificationManager.createNotificationChannel(channel);
        }
    }

}
