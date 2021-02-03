package com.robot.baselibs.common.network

import android.content.Context
import androidx.collection.ArrayMap
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.AppUtils
import com.robot.baselibs.common.cookie.NovateCookieManger
import com.robot.baselibs.common.domain.HttpLoggingInterceptor
import com.robot.baselibs.common.download.SkDownloadInterceptor
import com.robot.baselibs.common.download.SkDownloadListenerImpl
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.fastjson.FastJsonConverterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 * describe Retrofit工具类
 * authors lvuchenLiu
 * createTime 2019/8/13 18:06
 *
 * modifier
 * endTime 2019/8/13 18:06
 */

class RetrofitClient(var context: Context) {

    /**
     * post方式的访问api
     * 参数只放入请求体里面
     */
    fun post(type: Int, url: String): Observable<String> {
        return post(type, url, ArrayMap<String, String>(), ArrayMap<String, String>())
    }

    /**
     * post方式的访问api
     * 参数只放入请求体里面
     */
    fun post(type: Int, url: String, map: Map<String, Any>): Observable<String> {
        return post(type, url, ArrayMap<String, String>(), map)
    }

    /**
     * post方式的访问api
     * 参数即可放入请求体里面亦可放入url后面
     * queryMap 放在url后面
     * bodyMap 放在表单内
     */
    fun post(
        type: Int,
        url: String,
        queryMap: Map<String, String>,
        bodyMap: Map<String, Any>
    ): Observable<String> {
        val service = retrofit.create(BaseRetrofitService::class.java)
        val body = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            JSONObject(bodyMap).toString()
        )

        return service.execPost(getHeaders(type), url, queryMap, body)
            .compose(schedulersTransformer())
    }

    /**
     * put方式的访问api
     * 参数只放入请求体里面
     */
    fun put(
        type: Int, url: String, map: Map<String, Any>
    ): Observable<String> {
        val service = retrofit.create(BaseRetrofitService::class.java)
        val body =
            RequestBody.create(
                "application/json; charset=utf-8".toMediaTypeOrNull(),
                JSONObject(map).toString()
            )
        return service.execPut(getHeaders(type), url, body).compose(schedulersTransformer())

    }

    /**
     * get方式的访问api
     */
    fun get(type: Int, url: String, map: Map<String, String>): Observable<String> {
        val service = retrofit.create(BaseRetrofitService::class.java)
        return service.execGet(getHeaders(type), url, map).compose(schedulersTransformer())
    }

    /**
     * get方式的访问api(连接处理)
     */
    fun get(type: Int, url: String): Observable<String> {
        return get(type, url, ArrayMap<String, String>()).compose(schedulersTransformer())
    }

    /**
     * get方式的访问api
     */
    fun getHead(type: Int, url: String, appVersion: String): Observable<String> {
        val service = retrofit.create(BaseRetrofitService::class.java)

        return service.execGet(getHeaders(type), url, ArrayMap<String, String>())
            .compose(schedulersTransformer())
    }

    fun downApk(url: String): Observable<ResponseBody> {
        val service = retrofit.create(BaseRetrofitService::class.java)
        return service.dowloadApk(url)
    }

    /**
     * post 上传图片(这个貌似没啥好说的)
     */
    fun upLoad(
        type: Int,
        url: String,
        params: HashMap<String, String>,
        body: MultipartBody.Part
    ): Observable<String> {
        val service = retrofit.create(BaseRetrofitService::class.java)
        return service.upLoad(getHeaders(type), url, params, body).compose(schedulersTransformer())
    }

    fun upLoadImgFile(
        type: Int,
        url: String,
        params: HashMap<String, String>,
        body: MultipartBody.Part
    ): Observable<String> {
        val service = retrofit.create(BaseRetrofitService::class.java)
        return service.upLoad(getHeaders(type), url, params, body).compose(schedulersTransformer())
    }

    // 虚拟线程
    private fun schedulersTransformer(): ObservableTransformer<String, String> {
        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    companion object {

        const val NONE = -1
        const val SIGN = 0
        const val TOKEN = 1

        private const val DEFAULT_TIMEOUT = 60

        const val HEADER_AUTH_TYPE = "internalAuthType"

        @get:Synchronized
        @set:Synchronized
        lateinit var instance: RetrofitClient

        @Synchronized
        fun init(context: Context) {
            instance = RetrofitClient(context.applicationContext)
        }

        fun <T> service(service: Class<T>): T {
            return retrofit.create(service)
        }

        /**
         * @param type -1是什么都不带，0是带token
         */
        fun getHeaders(type: Int): HashMap<String, String> {
            val headers = HashMap<String, String>()
            if (type == 0) {
                headers["token"] = UserInfoData.instens.token
            } else if (type == 1) {
                if (UserInfoData.instens.simple_token.isNotEmpty()) {
                    headers["simple_token"] = UserInfoData.instens.simple_token
                }
            }
            headers["platform"] = "0"
            headers["app-version"] = AppUtils.getAppVersionName()
            return headers
        }


        @JvmStatic
        val okhttpInstance: OkHttpClient
            get() {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY

                return OkHttpClient.Builder()
                        //设置可以从传入的HTTP响应接受cookie并向传出HTTP请求提供cookie的处理程序。
                        .cookieJar(NovateCookieManger(ActivityUtils.getTopActivity()))
                        //添加https证书
                        //.sslSocketFactory(new SslContextFactory().getSslSocket().getSocketFactory())
                        //链接超时
                        .connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                        .readTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                        //新链接默认超时时间
                        .writeTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                        //设置用于回收HTTP和HTTPS连接的连接池。 这里你可以根据自己的机型设置同时连接的个数和时间，我这里8个，和每个保持时间为10s
                        .connectionPool(ConnectionPool(15, 20, TimeUnit.SECONDS))
                        .addInterceptor(logging)
                        // 添加头部
                        .addInterceptor { chain ->
                            var request: Request = chain.request()
                            val authType = request.header(HEADER_AUTH_TYPE)
                            val builder = request.newBuilder()
                            builder.removeHeader("User-Agent")
                                    .addHeader("User-Agent", "Android")

                            request = builder.build()
                            chain.proceed(request)
                        }
                        .addInterceptor(SkDownloadInterceptor(SkDownloadListenerImpl()))
                        .build()
            }

        @JvmStatic
        val retrofit: Retrofit
            get() = Retrofit.Builder()
                    .client(okhttpInstance)
                    .baseUrl(NetWorkUrl.API)
                    //增加返回值为String的支持
                    .addConverterFactory(ScalarsConverterFactory.create())
                    //增加返回值为Gson的支持(以实体类返回)
                    .addConverterFactory(GsonConverterFactory.create())
                    //增加返回值为Observable<T>的支持
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
    }

}

