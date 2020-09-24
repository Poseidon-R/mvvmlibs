package com.robot.baselibs.common.push;

import android.content.Context;
import android.widget.Toast;

import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

public class FcjNotificationClickHandler extends UmengNotificationClickHandler {
    @Override
    public void launchApp(Context context, UMessage msg) {
        super.launchApp(context, msg);
    }

    @Override
    public void openUrl(Context context, UMessage msg) {
        super.openUrl(context, msg);
    }

    @Override
    public void openActivity(Context context, UMessage msg) {
        super.openActivity(context, msg);
    }

    @Override
    public void dealWithCustomAction(Context context, UMessage msg) {
        Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
    }
}
