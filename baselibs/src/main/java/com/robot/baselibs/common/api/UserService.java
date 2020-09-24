package com.robot.baselibs.common.api;

import com.qihang.annotations.ApiFactory;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

@ApiFactory(value = RobotBaseApi.class)
public interface UserService {

    /**
     * 登录
     *
     * @return
     */
    @POST("account/login")
    Observable<String> login(@Body Map<String, String> map);
}
