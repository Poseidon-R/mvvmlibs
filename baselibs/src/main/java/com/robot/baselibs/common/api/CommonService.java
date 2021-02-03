package com.robot.baselibs.common.api;

import com.qihang.annotations.ApiFactory;
import com.robot.baselibs.common.network.RetrofitClient;
import com.robot.baselibs.model.BaseResponse;
import com.robot.baselibs.model.SplashBannerBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 创建日期：2020/9/14  12:41
 * 类说明:
 *
 * @author：robot
 */
@ApiFactory(value = RetrofitClient.class)
public interface CommonService {

    /**
     *
     * @param params onlyId 唯一标识
     * @return
     */
    @POST("rest/splashActivity/listAppSplash")
    Observable<BaseResponse<SplashBannerBean>> listAppSplash(@Body Map<String, Object> params);

}
