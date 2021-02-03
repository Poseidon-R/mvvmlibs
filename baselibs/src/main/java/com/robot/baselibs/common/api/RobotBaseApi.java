package com.robot.baselibs.common.api;


import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import me.goldze.mvvmhabit.http.interceptor.logging.Level;
import me.goldze.mvvmhabit.http.interceptor.logging.LoggingInterceptor;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;


/**
 * @author robot
 * @since 2020/09/11
 * RetrofitApi 创建
 */

public class RobotBaseApi {
    private static final int DEFAULT_TIMEOUT = 10;

    /**
     * 接口域名地址
     */
    public static final String BASE_URL = "https://baidu.com/v1/app/";

//    public static final String API_SERVER_URL_DEV = "https://gongyu.picp.vip/";

//        public static final String API_SERVER_URL_DEV = "http://192.168.137.1:8081/";

    public static final String API_SERVER_URL_DEV = "http://wap.fivecmtech.com";
    public static final String API_SERVER_URL_FORMAL = "https://api.fcj.maison-huis.com/fcj-portal/";
    public static final String API_SERVER_URL_360 = "https://api.fcj.maison-huis.com/fcj-portal/";
    public static final String API_SERVER_URL_REG = "https://api.release.9squareinches.com/fcj-portal/";
    public static final String API_SERVER_URL = API_SERVER_URL_DEV;


    public static final String PIC_BASE_URL_DEV = "https://fcj.oss-accelerate.aliyuncs.com/";
    public static final String PIC_BASE_URL_FORMAL = "https://fcj.oss-cn-hangzhou.aliyuncs.com/";
    public static final String PIC_BASE_URL_360 = "https://fcj.oss-cn-hangzhou.aliyuncs.com/";
    public static final String PIC_BASE_URL_REG = "https://fcj.oss-cn-hangzhou.aliyuncs.com/";
    public static final String PIC_BASE_URL = PIC_BASE_URL_DEV;


    public static Retrofit getRetrofit() {
        Retrofit sRetrofit = new Retrofit.Builder()
                .baseUrl(API_SERVER_URL)
                .addConverterFactory(com.robot.baselibs.common.api.FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkhttpInstance())
                .build();
        return sRetrofit;
    }

    /**
     * 指定域名RetofitClient
     *
     * @param baseurl
     * @return
     */
    public static Retrofit getRetrofit(String baseurl) {
        return new Retrofit.Builder()
                //.addConverterFactory(FastJsonConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .addConverterFactory(FastJsonConverterFactory.create())
                .client(getOkhttpInstance())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    public static OkHttpClient getOkhttpInstance() {
//        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        LoggingInterceptor httpLoggingInterceptor = new LoggingInterceptor.Builder()
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request("Request")
                .response("Response")
                .build();
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.addInterceptor(httpLoggingInterceptor);
        OkHttpClient sOkHttpClient = builder.build();

        return sOkHttpClient;
    }

//
//    public static OkHttpClient getOkhttpInstance() {
//        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        Cache cache = new Cache(RobotApplication.getContext().getCacheDir(), 10 * 1024 * 1024);
//
//        OkHttpClient
//                okHttpClient = new OkHttpClient.Builder().readTimeout(7000, TimeUnit.MILLISECONDS)
//                .connectTimeout(7000, TimeUnit.MILLISECONDS)
//                .addInterceptor(httpLoggingInterceptor)
//                .addInterceptor(new GzipRequestInterceptor())
//                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
//                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
//                .addNetworkInterceptor(new StethoInterceptor())
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Request request = chain.request();
//                        if (!NetworkUtils.isAvailable()) {
//                            int maxStale = 7 * 24 * 60 * 60; // 离线时缓存保存1周
//                            CacheControl tempCacheControl = new CacheControl.Builder().onlyIfCached().maxStale(maxStale, TimeUnit.SECONDS).build();
//                            request = request.newBuilder().cacheControl(tempCacheControl).build();
//                        }
//                        return chain.proceed(request);
//                    }
//                })
//                .cache(cache)
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        if (NetworkUtils.isAvailable()) {
//                            LogUtils.d("-----------NetworkUtils.isAvailable()");
//                            return chain.proceed(chain.request());
//                        } else {
//                            LogUtils.d("-----------AppNetErrorException");
//                            throw new AppNetErrorException();
//                        }
//                    }
//                })
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Request original = chain.request();
////        HttpUrl url = original.url().newBuilder().addQueryParameter("deviceId", DeviceUtils.getAndroidID()).build(); //添加请求头
//                        String userToken = "";
//                        if (!TextUtils.isEmpty(PrefsManager.getUserLoginInfo().getToken())) {
//                            userToken = PrefsManager.getUserLoginInfo().getToken();
//                        }
//                        Request request = original;
//                        Request.Builder builder = original.newBuilder();
//                        if (!TextUtils.isEmpty(userToken)) {
//                            builder.addHeader("token", userToken);
//                        }
//                        builder.addHeader("version", AppUtils.getAppVersionName());
//                        Log.d("HeadParams-----","token:"+userToken);
//                        request = builder.method(original.method(), original.body()).build();
//                        return chain.proceed(request);
//                    }
//                })
//                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
//                .build();
//        return okHttpClient;
//    }

}
