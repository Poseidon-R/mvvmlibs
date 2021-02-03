package com.robot.baselibs.exception

import android.net.ParseException
import com.alibaba.fastjson.JSON
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.net.ssl.SSLHandshakeException

/**
 * 异常格式化
 *
 * @author leaflc
 */
object ExceptionHandle {
    /**
     * 处理异常
     *
     * @param e Throwable
     * @return 自定义的LocalException
     */
    @JvmStatic
    fun handleException(e: Throwable?): Exception {
        val ex: LocalException
        if (e is LocalException) {
            ex = e
        } else if (e is HttpException) {
            val httpException = e
            ex = LocalException(e, ERROR.HTTP_ERROR)
            ex.errorCode = httpException.code()
            ex.msg = when (e.code()) {
                ApiErrorType.INTERNAL_SERVER_ERROR.code ->
                    ApiErrorType.INTERNAL_SERVER_ERROR.name
                ApiErrorType.BAD_GATEWAY.code ->
                    ApiErrorType.BAD_GATEWAY.name
                ApiErrorType.NOT_FOUND.code ->
                    ApiErrorType.NOT_FOUND.name
                else -> {
                    ApiErrorType.UNEXPECTED_ERROR.name
                }
            }
        } else if (e is JSONException
                || e is com.alibaba.fastjson.JSONException
                || e is ParseException) {
            ex = LocalException(e, ERROR.PARSE_ERROR)
            ex.msg = "解析错误"
        } else if (e is ConnectException) {
            ex = LocalException(e, ERROR.NETWORD_ERROR)
            ex.msg = "连接失败"
        } else if (e is SSLHandshakeException) {
            ex = LocalException(e, ERROR.SSL_ERROR)
            ex.msg = "证书验证失败"
        } else if (e is ConnectTimeoutException) {
            ex = LocalException(e, ERROR.TIMEOUT_ERROR)
            ex.msg = "连接超时"
        } else if (e is SocketTimeoutException) {
            ex = LocalException(e, ERROR.TIMEOUT_ERROR)
            ex.msg = "连接超时"
        } else {
            ex = LocalException(e, ERROR.UNKNOWN)
            ex.msg = "网络异常"
        }
        return ex
    }

    /**
     * 约定异常
     */
    internal interface ERROR {
        companion object {
            /**
             * 未知错误
             */
            const val UNKNOWN = 1000

            /**
             * 解析错误
             */
            const val PARSE_ERROR = 1001

            /**
             * 网络错误
             */
            const val NETWORD_ERROR = 1002

            /**
             * 协议出错
             */
            const val HTTP_ERROR = 1003

            /**
             * 证书出错
             */
            const val SSL_ERROR = 1005

            /**
             * 连接超时
             */
            const val TIMEOUT_ERROR = 1006
        }
    }
}