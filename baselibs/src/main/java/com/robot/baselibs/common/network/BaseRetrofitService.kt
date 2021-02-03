package com.robot.baselibs.common.network

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

/**
 * describe Retrofit抽象方法
 * authors lvuchenLiu
 * createTime 2019/8/13 18:03
 *
 * modifier
 * endTime 2019/8/13 18:03
 */

interface BaseRetrofitService {

    /**
     * get方式 map传参
     */
    @GET("{url}")
    fun execGet(
        @HeaderMap headers: Map<String, String>,
        @Path(value = "url", encoded = true) url: String,
        @QueryMap maps: Map<String, String>
    ): Observable<String>


    /**
     * post方式 URL上添加参数 json传参
     */
    @POST("{url}")
    fun execPost(
        @HeaderMap headers: Map<String, String>,
        @Path(value = "url", encoded = true) url: String,
        @QueryMap maps: Map<String, String>,
        @Body body: RequestBody
    ): Observable<String>

    /**
     * put方式 json传参
     */
    @PUT("{url}")
    fun execPut(
        @HeaderMap headers: Map<String, String>,
        @Path(value = "url", encoded = true) url: String,
        @Body body: RequestBody
    ): Observable<String>


    /**
     * 上传图片
     * @param url
     * @param Body
     * @return
     */
    @Multipart
    @POST("{url}")
    fun upLoad(
        @HeaderMap headers: Map<String, String>,
        @Path(value = "url", encoded = true) url: String,
        @QueryMap params: Map<String, String>,
        @Part file: MultipartBody.Part
    ): Observable<String>

    @Streaming
    @GET
    fun dowloadApk(@Url url: String): Observable<ResponseBody>
}
