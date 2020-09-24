package com.robot.baselibs.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

/**
 * Created by roczheng on 2019/3/21.
 */

public class CheckApkExist {
    private static String weiboPkgName = "com.sina.weibo";
    private static String qqPkgName = "com.tencent.mobileqq";
    private static Context mContext = null;

    public static boolean checkApkExist(String packageName) {
        if (TextUtils.isEmpty(packageName))
            return false;
        try {
            if (mContext != null) {
                ApplicationInfo info = mContext.getPackageManager()
                        .getApplicationInfo(packageName,
                                PackageManager.GET_UNINSTALLED_PACKAGES);
            }
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static boolean checkweboExist(Context context) {
        mContext = context;
        return checkApkExist(weiboPkgName);
    }

    public static boolean checkqqExist(Context context) {
        mContext = context;
        return checkApkExist(qqPkgName);
    }

    public static void releaseMemory() {
        mContext = null;
    }
}
