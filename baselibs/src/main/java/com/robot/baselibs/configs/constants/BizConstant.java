package com.robot.baselibs.configs.constants;

public class BizConstant {

    public static int HOT_CATE_ID = 9999;


    public static final int ORI_VER = 1;
    public static final int ORI_HORI = 2;
    public static String DIR_APK;

    //bugly app key
    public static final String BUGLY_APPKEY = "891d82c5fc";
    public static final String UMENG_APPKEY = "5d5672c84ca3578086000cdd";
    public static final String UMENG_MSG_SECRET = "a5dd929a10c8682156073e6fd8ce1ea2";
    public static final String NETEASE_QIYU_APPKEY = "3021f1a85ee22d52317aa7bdec3dd7b1";
    //相关app idwxa0047becc08f5c0a
    public static final String WX_APP_ID = "wxa0047becc08f5c0a";
    public static final String WX_APP_SECRET = "8b3e1e44a4b591ac2d21586355800be3";
    public static final String WEIBO_APP_ID = "1085676763";
    public static final String WEIBO_APP_SECRET = "d3f9ce54a8f18c9296658f1b0fb984ed";
    public static final String WEIBO_REDIRECT_URL = "http://www.9squareinches.com/";
    public static final String QQ_APP_ID = "101762222";
    public static final String QQ_APP_SECRET = "a6f9f0ec670248cf0f42ccec6c508444";

    public final static String DEFAULT_CHANNEL = "DEFAULT_CHANNEL";


    public static final int TYPE_RED_PACKET_COMING = 1;
    public static final int TYPE_RED_PACKET_GET = 2;
    public static final int TYPE_RED_PACKET_OUT_OF_DATE = 3;
    public static final int TYPE_RED_PACKET_USED = 4;

    //1：在路上 2：已获得 3：已失效 4:已使用
    public static final int TYPE_BONUS_COMING = 1;
    public static final int TYPE_BONUS_GET = 2;
    public static final int TYPE_BONUS_OUT_OF_DATE = 3;
    public static final int TYPE_BONUS_USED = 4;

    public static final int TYPE_EVALUATION = 0;
    public static final int TYPE_SUBJECT = 1;
    public static final int TYPE_INFORMATION = 2;

    public static final int TYPE_NEW_SHARE_LIST = 0;
    public static final int TYPE_HOT_SHARE_LIST = 1;


    public static final int TYPE_COMMENT_ALL = 1;
    public static final int TYPE_COMMENT_PIC = 2;

    public static final int TYPE_EVALUATION_ALL = 1;
    public static final int TYPE_EVALUATION_IN_GOODS = 2;


    public static final int LOCAL_PAY_TYPE_ORDER = 1;
    public static final int LOCAL_PAY_TYPE_MEMBER = 2;

    public static final int CATEGORY_DETAIL = 1;
    public static final int INFO_DETAIL = 2;


    public static final int HOR_CATE_FROM_MAP = 1;
    public static final int HOR_CATE_FROM_GOODS = 2;
    public static final int HOR_CATE_FROM_CATE = 3;


    public static final String CHANNEL_FCJ_NOTIFICATION = "channel_fcj";
    public static final String TARGET_INTENT_KEY = "TARGET_INTENT_KEY";
    public static final String PUSH_INFO_KEY = "PUSH_INFO_KEY";

    public static final int WECHAT_LOGIN = 1;
    public static final int QQ_LOGIN = 2;
    public static final int WEIBO_LOGIN = 3;

    public static final String UPDATE_BEAN = "UPDATE_BEAN";
    public static final String DOWNLOAD_URL = "DOWNLOAD_URL";
    public static final String DOWNLOAD_VERSION = "DOWNLOAD_VERSION";

    /**
     * ======================================push event====================================================
     **/
    //申请成功或者失败
    public static final int BROADCAST_OPTION_ONE = 0001;
    //1：文章 2:商品 3：晒单 4:红包 5: 返现 6：积分7：订单详情页 8：退款详情页
    public static final int PUSH_JUMP_ARTICLE_DETAIL = 1;
    public static final int PUSH_JUMP_GOODS_DETAIL = 2;
    public static final int PUSH_JUMP_SHARE_DETAIL = 3;
    public static final int PUSH_JUMP_RED_PACKET = 4;
    public static final int PUSH_JUMP_BONUS = 5;
    public static final int PUSH_JUMP_POINT_COIN = 6;
    public static final int PUSH_JUMP_ORDER_DETAIL = 7;
    public static final int PUSH_JUMP_RETURN_DETAIL = 8;


    public static final int RC_CAMERA_AND_STORAGE = 1001;

    public static final int REQ_ANDROID_TYPE = 0;

}
