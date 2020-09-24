package com.robot.baselibs.configs;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.robot.baselibs.RobotApplication;
import com.robot.baselibs.configs.constants.Constants;
import com.robot.baselibs.utils.json.JSONParseUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: kang
 * @Date: 2019-03-07 14:50
 * @Name: ConfigInfoManager  preference config数据的管理
 * @Description:
 */
public class ConfigInfoManager {

    private static volatile ConfigInfoManager instance = null;

    public static final ConfigInfoManager getInstance() {
        if (instance == null) {
            synchronized (ConfigInfoManager.class) {
                if (instance == null) {
                    instance = new ConfigInfoManager();
                }
            }
        }

        return instance;
    }


    public boolean getAppShowGuideView() {
        return PreferencesUtils.getBoolean(RobotApplication.getContext(), Constants.APP_SHOW_GUIDE_VIEW, true);
    }

    public void setAppShowGuideView(boolean appGuideView) {
        PreferencesUtils.putBoolean(RobotApplication.getContext(), Constants.APP_SHOW_GUIDE_VIEW, appGuideView);
    }

    public boolean getAppShowGuideViewForUser() {
        return PreferencesUtils.getBoolean(RobotApplication.getContext(), Constants.APP_SHOW_GUIDE_VIEW_FOR_USER, true);
    }

    public void setAppShowGuideViewForUser(boolean appGuideViewForUser) {
        PreferencesUtils.putBoolean(RobotApplication.getContext(), Constants.APP_SHOW_GUIDE_VIEW_FOR_USER, appGuideViewForUser);
    }

    public long getCalShowTime() {
        return PreferencesUtils.getLong(RobotApplication.getContext(), Constants.CAL_SHOW_TIME, 0);
    }

    public void setCalShowTime(long time) {
        PreferencesUtils.putLong(RobotApplication.getContext(), Constants.CAL_SHOW_TIME, time);
    }

    public void saveSearchHistory(List<String> historys) {
        Gson gson = new Gson();
        if (historys == null) {
            PreferencesUtils.putString(RobotApplication.getContext(), Constants.SEARCH_HISTORY, "");
            return;
        }
        PreferencesUtils.putString(RobotApplication.getContext(), Constants.SEARCH_HISTORY, gson.toJson(historys));
    }

    public List<String> getSearchHistory() {
        String gsonstring = PreferencesUtils.getString(RobotApplication.getContext(), Constants.SEARCH_HISTORY, "");
        if (TextUtils.isEmpty(gsonstring)) return new ArrayList<>();
        return JSONParseUtils.parseList(gsonstring, String.class);
    }


    public String getVipView() {
        return PreferencesUtils.getString(RobotApplication.getContext(), Constants.VIP_VIEW, "off");
    }

    public void setVipView(String vipView) {
        PreferencesUtils.putString(RobotApplication.getContext(), Constants.VIP_VIEW, vipView);
    }

    public String getUpdateVersion() {
        return PreferencesUtils.getString(RobotApplication.getContext(), Constants.UPDATE_VERSION, "");
    }

    public void setUpdateVersion(String updateVersion) {
        PreferencesUtils.putString(RobotApplication.getContext(), Constants.UPDATE_VERSION, updateVersion);
    }


    public boolean isShowUpdate() {
        return PreferencesUtils.getBoolean(RobotApplication.getContext(), Constants.IS_SHOW_UPDATE, true);
    }

    public void setShowUpdate(boolean showUpdate) {
        PreferencesUtils.putBoolean(RobotApplication.getContext(), Constants.IS_SHOW_UPDATE, showUpdate);
    }

    public boolean getAppSplashAgreement() {
        return PreferencesUtils.getBoolean(RobotApplication.getContext(), Constants.APP_SPLASH_AGREEMENT, false);
    }

    public void setAppSplashAgreement(boolean appSplashAgreement) {
        PreferencesUtils.putBoolean(RobotApplication.getContext(), Constants.APP_SPLASH_AGREEMENT, appSplashAgreement);
    }

    public boolean getHomeScanClick() {
        return PreferencesUtils.getBoolean(RobotApplication.getContext(), Constants.HOME_SCAN_CLICK, false);
    }

    public void setHomeScanClick(boolean homeScanClick) {
        PreferencesUtils.putBoolean(RobotApplication.getContext(), Constants.HOME_SCAN_CLICK, homeScanClick);
    }

    public String getAppLastExitTime() {
        return PreferencesUtils.getString(RobotApplication.getContext(), Constants.APP_LAST_EXIT_TIME);
    }

    public void setAppLastExitTime(String lastExitTime) {
        PreferencesUtils.putString(RobotApplication.getContext(), Constants.APP_LAST_EXIT_TIME, lastExitTime);
    }

    public int getHomeGoodsRandom() {
        return PreferencesUtils.getInt(RobotApplication.getContext(), Constants.HOME_GOODS_RANDOM, -1);
    }

    public void setHomeGoodsRandom(int homeGoodsRandom) {
        PreferencesUtils.putInt(RobotApplication.getContext(), Constants.HOME_GOODS_RANDOM, homeGoodsRandom);
    }

//    public void saveLoginUserInfo(UserLoginInfoBean userLoginInfoBean) {
//        Gson gson = new Gson();
//        if (userLoginInfoBean == null) {
//            PreferencesUtils.putString(FireBirdApplication.applicationInstance(), PrefConstants.USER_LOGIN_BEAN, "");
//        } else {
//            PreferencesUtils.putString(FireBirdApplication.applicationInstance(), PrefConstants.USER_LOGIN_BEAN, gson.toJson(userLoginInfoBean));
//        }
//    }
//
//    public UserLoginInfoBean getLoginUserInfo() {
//        String gsonString = PreferencesUtils.getString(FireBirdApplication.applicationInstance(), PrefConstants.USER_LOGIN_BEAN);
//        if (gsonString == null || gsonString.isEmpty()) {
//            return new UserLoginInfoBean();
//        }
//        Gson gson = new Gson();
//        return gson.fromJson(gsonString, UserLoginInfoBean.class);
//    }
//
//
//    public void saveMyTagList(List<TagBean> tagList) {
//        if (tagList != null) {
//            Gson gson = new Gson();
//            PreferencesUtils.putString(FireBirdApplication.applicationInstance(), PrefConstants.MY_TAG_LIST, gson.toJson(tagList));
//        } else {
//            PreferencesUtils.putString(FireBirdApplication.applicationInstance(), PrefConstants.MY_TAG_LIST, "");
//        }
//    }
//
//    public List<TagBean> getMyTagList() {
//        String gsonstring = PreferencesUtils.getString(FireBirdApplication.applicationInstance(), PrefConstants.MY_TAG_LIST, "");
//        if (TextUtils.isEmpty(gsonstring)) return new ArrayList<TagBean>();
//        return JSONParseUtils.parseList(gsonstring, TagBean.class);
//    }


}
