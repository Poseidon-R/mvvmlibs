package com.robot.baselibs.common.network

import android.util.AndroidRuntimeException
import com.blankj.utilcode.util.LogUtils
import io.reactivex.observers.DisposableObserver
import org.clc.app.util.arouter.ARouterPath
import org.clc.app.util.arouter.ARouterUtil

interface IObserver<T> {
    var tag: String?
    var canRepeat: Boolean
    var showToast: Boolean

    /**
     * 检查登录
     */
    fun checkLogin(t: NetWorkBean): Boolean {
        return if (t.code == HttpCode.TOKEN_OVERDUE1 || t.code == HttpCode.TOKEN_OVERDUE2) {
            //token失效
            try {
                LogUtils.e("token失效")
                ARouterUtil.goToActivity(ARouterPath.LoginModuleAty)
//                ARouterUtil.goToActivityType(ARouterPath.LoginModuleAty, Intent.FLAG_ACTIVITY_CLEAR_TASK)
            } catch (e: AndroidRuntimeException) {
                LogUtils.e("token失效")
                e.printStackTrace()
                ARouterUtil.goToActivity(ARouterPath.LoginModuleAty)
            }

            false
        } else {
            true
        }
    }

    fun addTag(observer: DisposableObserver<String>) {
        if (!canRepeat && tag != null) {
            ApiTagManager.instance.add(tag!!, observer)
        }
    }

    fun removeTag() {
        canRepeat = false
        if (!canRepeat && tag != null) {
            ApiTagManager.instance.remove(tag!!)
        }
    }
}