package com.robot.glass.app;

import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.liys.doubleclicklibrary.helper.ViewDoubleHelper;
import com.qiyukf.unicorn.api.StatusBarNotificationConfig;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFOptions;
import com.robot.baselibs.BuildConfig;
import com.robot.glass.R;
import com.robot.baselibs.common.network.NetworkStateObserver;
import com.robot.baselibs.common.push.FcjMessageHandler;
import com.robot.baselibs.common.push.FcjNotificationClickHandler;
import com.robot.baselibs.configs.PrefsManager;
import com.robot.baselibs.configs.constants.BizConstant;
import com.robot.baselibs.util.AppUtils;
import com.robot.baselibs.util.DensityAdaptationUtils;
import com.robot.baselibs.utils.ChannelUtil;
import com.robot.baselibs.utils.pic.QiyuImageLoader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import me.goldze.mvvmhabit.base.BaseApplication;
import me.goldze.mvvmhabit.crash.CaocConfig;
import me.goldze.mvvmhabit.utils.KLog;
import me.goldze.mvvmhabit.utils.Utils;

public class RobotApplication extends BaseApplication {

    private static RobotApplication context;

    private static final String UM_KEY = "586c904907fe656adc00197a";

    public static final String WX_KEY = "wx3ba86c39e9dd9309";
    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        //友盟配置初始化
        UMConfigure.setLogEnabled(true);
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, UM_KEY);
        //配置友盟微信key
        PlatformConfig.setWeixin(WX_KEY, "9bcac451ac21ba1c049b4f61fcecb394");
        PrefsManager.load(this);
        NetworkStateObserver.getInstance().initialize(this);

        AppUtils.init(this);
        Utils.init(this);
        //是否开启日志打印
        KLog.init(true);
//配置全局异常崩溃操作
        DensityAdaptationUtils.setDensity(context, 375);
        CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //背景模式,开启沉浸式
                .enabled(true) //是否启动全局异常捕获
                .showErrorDetails(true) //是否显示错误详细信息
                .showRestartButton(true) //是否显示重启按钮
                .trackActivities(true) //是否跟踪Activity
                .minTimeBetweenCrashesMs(2000) //崩溃的间隔时间(毫秒)
                .errorDrawable(R.mipmap.ic_launcher) //错误图标
//                .restartActivity(LoginActivity.class) //重新启动后的activity
                //.errorActivity(YourCustomErrorActivity.class) //崩溃后的错误activity
                //.eventListener(new YourCustomEventListener()) //崩溃后的错误监听
                .apply();
        initQiyu();
        initDir();

        initBugly();
        initUMShare();
        initUMPush();
        initDoubleClick();


    }

    private void initUMShare() {
        /**
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:【友盟+】 AppKey
         * 参数3:【友盟+】 Channel
         * 参数4:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数5:Push推送业务的secret
         */
        UMConfigure.init(this, BizConstant.UMENG_APPKEY, ChannelUtil.getChannel(this, BizConstant.DEFAULT_CHANNEL), UMConfigure.DEVICE_TYPE_PHONE, BizConstant.UMENG_MSG_SECRET);
        UMShareAPI.get(this);//初始化sdk
        PlatformConfig.setWeixin(BizConstant.WX_APP_ID, BizConstant.WX_APP_SECRET);
        //新浪微博(第三个参数为回调地址)
        PlatformConfig.setSinaWeibo(BizConstant.WEIBO_APP_ID, BizConstant.WEIBO_APP_SECRET, BizConstant.WEIBO_REDIRECT_URL);
        //QQ
        PlatformConfig.setQQZone(BizConstant.QQ_APP_ID, BizConstant.QQ_APP_SECRET);
    }
    private void initDoubleClick() {
        ViewDoubleHelper.init(this, 500);
    }

    private void initUMPush() {
        //获取消息推送代理示例
        PushAgent mPushAgent = PushAgent.getInstance(this);
//注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                LogUtils.d("deviceToken====" + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
            }
        });
        mPushAgent.setMessageHandler(new FcjMessageHandler());
        mPushAgent.setNotificationClickHandler(new FcjNotificationClickHandler());
    }
    private void initBugly() {
        Bugly.init(this, BizConstant.BUGLY_APPKEY, BuildConfig.DEBUG);
//        CrashReport.setIsDevelopmentDevice(this, BuildConfig.DEBUG);
        CrashReport.setAppChannel(getApplicationContext(), ChannelUtil.getChannel(this, "DEFAULT_CHANNEL"));
        CrashReport.setAppVersion(getApplicationContext(), com.blankj.utilcode.util.AppUtils.getAppVersionName());
        CrashReport.initCrashReport(getApplicationContext(), BizConstant.BUGLY_APPKEY, BuildConfig.DEBUG);
    }
    private void initDir() {
        BizConstant.DIR_APK = getFilesDir() + "/apk";
    }

    private void initQiyu() {
        Unicorn.init(this, BizConstant.NETEASE_QIYU_APPKEY, options(), new QiyuImageLoader());
    }
    // 如果返回值为null，则全部使用默认参数。
    private YSFOptions options() {
        YSFOptions options = new YSFOptions();
        options.statusBarNotificationConfig = new StatusBarNotificationConfig();
        return options;
    }
    public static Context getContext() {
        return context;
    }

}
