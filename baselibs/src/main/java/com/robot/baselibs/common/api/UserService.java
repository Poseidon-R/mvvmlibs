package com.robot.baselibs.common.api;

import com.qihang.annotations.ApiFactory;
import com.robot.baselibs.common.network.RetrofitClient;
import com.robot.baselibs.model.BaseResponse;
import com.robot.baselibs.pojo.LoginEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

@ApiFactory(value = RobotBaseApi.class)
public interface UserService {

    /**
     * 登录 account，pwd
     *
     * @return
     */
    @POST("/bmw/user/login")
    @FormUrlEncoded
    Observable<BaseResponse<LoginEntity>> login(@FieldMap Map<String, Object> map);


}
