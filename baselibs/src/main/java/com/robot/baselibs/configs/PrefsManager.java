package com.robot.baselibs.configs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.robot.baselibs.model.SplashBannerBean;
import com.robot.baselibs.model.UserInfoBean;
import com.robot.baselibs.model.UserLoginInfoEntity;

import static android.content.Context.MODE_PRIVATE;

public class PrefsManager {

    protected static SharedPreferences mPreferences;

    public static SharedPreferences getMPreferences() {
        return mPreferences;
    }

    protected static SharedPreferences.Editor mEditor;
    private static final String PREFERENCE_FILE_NAME = "base";

    @SuppressLint("CommitPrefEdits")
    public static void load(Context a) {
        try {
            mPreferences = a.getSharedPreferences(PREFERENCE_FILE_NAME, MODE_PRIVATE);
            mEditor = mPreferences.edit();
        } catch (Exception e) {
        }
    }

    public static boolean save() {
        if (mEditor == null) {
            return false;
        }

        return mEditor.commit();
    }

    public static boolean clear() {
        if (mEditor == null) {
            return false;
        }
        mEditor.clear();
        return mEditor.commit();
    }

    private static final String LOGIN_STATE = "mlstate";
    public static final String LOGINING = "dlld";
    public static final String LOGINOUT = "zxld";
    public static final String NOTLOGIN = "mydld";

    public static final int LOGIN_VIA = -1;

    public static void loginSuccess() {

        if (mEditor == null) {
            return;
        }

        mEditor.putString(LOGIN_STATE, LOGINING);
        mEditor.commit();

    }

    public static void loginout() {

        if (mEditor == null) {
            return;
        }
        mEditor.putString(LOGIN_STATE, LOGINOUT);
        mEditor.commit();

    }

    public static String getLoginType() {

        if (mPreferences == null) {
            return "";
        }
        return mPreferences.getString(LOGIN_STATE, NOTLOGIN);
    }


    public static final String USERILOGININFO = "userLoginInfo";

    public static void saveUserLoginInfo(UserLoginInfoEntity data) {

        if (mEditor == null) {
            return;
        }
        if (null == data) {
            mEditor.putString(USERILOGININFO, "");
        } else {
            mEditor.putString(USERILOGININFO, JSON.toJSONString(data));
        }
        mEditor.commit();
    }


    public static UserLoginInfoEntity getUserLoginInfo() {
        if (mPreferences == null) {
            return new UserLoginInfoEntity();
        }
        String ss = mPreferences.getString(USERILOGININFO, null);
        if (!TextUtils.isEmpty(ss)) {
            return JSON.parseObject(ss, UserLoginInfoEntity.class);
        } else {
            return new UserLoginInfoEntity();
        }
    }


    public static final String USERINFO = "userInfo";

    public static void saveUserInfo(UserInfoBean data) {

        if (mEditor == null) {
            return;
        }
        if (null == data) {
            mEditor.putString(USERINFO, "");
        } else {
            mEditor.putString(USERINFO, JSON.toJSONString(data));
        }

        mEditor.commit();
    }


    public static UserInfoBean getUserInfo() {

        if (mPreferences == null) {
            return new UserInfoBean();
        }
        String ss = mPreferences.getString(USERINFO, null);
        if (!TextUtils.isEmpty(ss)) {
            return JSON.parseObject(ss, UserInfoBean.class);
        } else {
            return new UserInfoBean();
        }
    }

    public static final String SPLASH_BANNER = "SPLASH_BANNER";

    public static void saveSplashBanner(SplashBannerBean bean) {

        if (mEditor == null) {
            return;
        }
        if (null == bean) {
            mEditor.putString(SPLASH_BANNER, "");
        } else {
            mEditor.putString(SPLASH_BANNER, JSON.toJSONString(bean));
        }
        mEditor.commit();
    }


    public static SplashBannerBean getSplashBean() {

        if (mPreferences == null) {
            return null;
        }
        String splashInfo = mPreferences.getString(SPLASH_BANNER, null);
        if (!TextUtils.isEmpty(splashInfo)) {
            return JSON.parseObject(splashInfo, SplashBannerBean.class);
        } else {
            return null;
        }
    }


}
