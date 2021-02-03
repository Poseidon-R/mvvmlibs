package com.robot.baselibs.common.network

import android.os.NetworkOnMainThreadException
import com.alibaba.fastjson.JSON
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.ToastUtils
import com.robot.baselibs.App
import com.robot.baselibs.util.Util
import io.reactivex.observers.DisposableObserver
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 *@showToast 是否显示错误toast
 *@tag: 请求标记,传入url
 */
abstract class NormalObserver<T>(
    override var showToast: Boolean = true,
    override var tag: String? = null
) :
    DisposableObserver<String>(), IObserver<T> {

    override var canRepeat: Boolean = false

    /**
     * 设置可以重复请求.默认禁止
     */
    fun setCanRepeat(): NormalObserver<T> {
        canRepeat = true
        return this
    }

    override fun onStart() {
        if (!NetWorkUtils.isNetConnected(App.getInstance())) {
            NetWorkUtils.showDialog("网络异常")
            return
        }
        addTag(this)
    }

    final override fun onComplete() {
        removeTag()
        onFinish()
    }

    final override fun onError(e: Throwable) {
        LogUtils.e("网络：$e")
        removeTag()
        var code = ""
        val apiError = if (e is HttpException) { //连接成功但后台返回错误状态码
            when (e.code()) {
                ApiErrorType.INTERNAL_SERVER_ERROR.code ->
                    ApiErrorType.INTERNAL_SERVER_ERROR
                ApiErrorType.BAD_GATEWAY.code ->
                    ApiErrorType.BAD_GATEWAY
                ApiErrorType.NOT_FOUND.code ->
                    ApiErrorType.NOT_FOUND
                else -> {
                    code = e.code().toString()
                    ApiErrorType.UNEXPECTED_ERROR
                }
            }
        } else {
            when (e) {//发送网络问题或其它未知问题
                is UnknownHostException -> ApiErrorType.NETWORK_NOT_CONNECT
                is ConnectException -> ApiErrorType.NETWORK_NOT_CONNECT
                is SocketTimeoutException -> ApiErrorType.CONNECTION_TIMEOUT
                is NetworkOnMainThreadException -> ApiErrorType.CONNECTION_TIMEOUT
                else -> ApiErrorType.UNEXPECTED_ERROR
            }
        }
        if (showToast) {
            NetWorkUtils.showDialog(
                apiError.message + "${if (!StringUtils.isEmpty(code)) {
                    "错误代码" + code
                } else {
                    ""
                }
                }"
            )
        }
        onFailure(null, apiError.code, apiError.name, tag)
    }


    final override fun onNext(t: String) {
        LogUtils.e("网络：$t")
        val bean = JSON.parseObject(t, NetWorkBean::class.java)
        if (bean != null) {
            if (bean.msg == null) {
                bean.msg = ""
            }
            if (tag == "getMsg" && bean.code != 0) {//获取红包消息不走token失效
                return
            }

            if (bean.code == HttpCode.UPDATE) {
                bean.msg = ""
                onFinish()
                Util.checkVersion()
                return
            }

            if (checkLogin(bean)) {
                if (bean.code == 0) {
                    onSuccess(t, bean.code, bean.msg, tag)
                } else {
                    if (showToast) {
                        NetWorkUtils.showDialog(bean.msg!!)
                    }
                    onFailure(t, bean.code, bean.msg, tag)
                }
            } else {
                if (bean.code == HttpCode.TOKEN_OVERDUE1 || bean.code == HttpCode.TOKEN_OVERDUE2) {
                    bean.msg = "请重新登录"
                }
                onFailure(t, bean.code, bean.msg, tag)
                if (showToast) {
                    ToastUtils.showShort(bean.msg!!)
                }
            }

        } else {
            onFailure(t, -1, "数据偷跑了，请重新请求", tag)
            if (showToast) {
                NetWorkUtils.showDialog("数据偷跑了，请重新请求")
            }
        }
    }

    protected abstract fun onSuccess(info: String?, code: Int, message: String?, tag: Any?)

    protected open fun onFailure(info: String?, code: Int, message: String?, tag: Any?) {
    }

    protected open fun onFinish() {}
}