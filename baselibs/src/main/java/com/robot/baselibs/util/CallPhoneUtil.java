package com.robot.baselibs.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.robot.baselibs.R;
import com.robot.baselibs.base.callback.RobotCallBackBoolean;
import com.robot.baselibs.view.CommonDialog;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.List;


/**
 * 如果仅仅是拨打电话，可以使用IntentUtil,这里就是多处使用尝试封装玩玩
 */
public class CallPhoneUtil {

    public static CommonDialog dialog;

    public static void callPhoneAndPermission(final Activity context, final String phoneNumber) {

        if (AndPermission.hasPermissions(context, Permission.CALL_PHONE)) {
            callPhoneWithDialog(context, phoneNumber);
        } else {
            AndPermission
                    .with(context)
                    .runtime()
                    .permission(Permission.CALL_PHONE)
                    .onGranted(new Action<List<String>>() {
                        @SuppressLint("MissingPermission")
                        @Override
                        public void onAction(List<String> data) {
                            callPhoneWithDialog(context, phoneNumber);
                        }

                    }).onDenied(new Action<List<String>>() {
                @Override
                public void onAction(List<String> data) {
                    Toast.makeText(context, "同意权限才可以拨打电话", Toast.LENGTH_LONG).show();
                }
            }).start();
        }
    }

    private static void callPhoneWithDialog(final Activity context, final String phoneNumber) {
        if (dialog != null && dialog.isShowing())
            return;
        CommonDialog.Builder builder = new CommonDialog.Builder(context);

        builder.setPositiveButton("拨打", R.color.colorPrimary, new RobotCallBackBoolean() {
            @Override
            public void action(int type) {
                callPhone(phoneNumber, context);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", R.color.color14, new RobotCallBackBoolean() {
            @Override
            public void action(int type) {
                dialog.dismiss();
            }
        });

        builder.setTitle("拨打："+phoneNumber);
        dialog = builder.create();
        dialog.show();
    }

    @SuppressLint("MissingPermission")
    private static void callPhone(String phoneNumber, Activity context) {

        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri uri = Uri.parse("tel:" + phoneNumber);
        intent.setData(uri);
        context.startActivity(intent);
    }

}

