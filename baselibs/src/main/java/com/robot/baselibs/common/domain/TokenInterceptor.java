package com.robot.baselibs.common.domain;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 网络请求头拦截器，添加统一的请求头
 * @author robot
 */
public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
//        originalRequest.newBuilder().header("Content-Type", "application/json;charset=UTF-8").build();
        return chain.proceed(originalRequest);
//        if (DKUserManager.getUserInfo() == null || !DKUserManager.isLogined()) {
//            return chain.proceed(originalRequest);
//        } else {
//            String accessToken = DKUserManager.getUserInfo().getAccessToken();
//            if (accessToken != null) {
//                Request requestAuthorised = originalRequest.newBuilder()
//                        .header("X-Access-Token", accessToken)
////                        .header("X-Access-Token", "989717d52fbcdbc16a7a10")
//                        .build();
//                return chain.proceed(requestAuthorised);
//            }
//            return chain.proceed(originalRequest);
//        }
    }
}
