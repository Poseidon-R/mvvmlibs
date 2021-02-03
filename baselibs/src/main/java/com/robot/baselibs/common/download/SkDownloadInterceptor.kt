package com.robot.baselibs.common.download

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * <pre>
 *     e-mail : 18721411287@163.com
 *     time   : 2018/7/10
 *     desc   :
 *     version: 1.0
 *     Copyright: Copyright（c）2017
 *     Company:
 * </pre>
 * @author niejunfeng
 */
class SkDownloadInterceptor(private val downloadListener: SkDownloadListener) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        return response.newBuilder().body(
                response.body?.let { SkResponseBody(responseBody = it, downloadListener = downloadListener) }
        ).build()
    }
}