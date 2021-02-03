package org.clc.app.util.arouter

import android.content.Context
import android.os.Bundle
import com.alibaba.android.arouter.facade.callback.NavCallback
import com.alibaba.android.arouter.launcher.ARouter

/**
 * describe 路由工具类
 * authors lvuchenLiu
 * createTime 2019/8/13 20:50
 *
 * modifier
 * endTime 2019/8/13 20:50
 */

object ARouterUtil {

    fun goToActivity(url: String) {
        ARouter.getInstance().build(url).navigation()
    }

    fun goToActivityCallBack(url: String, context: Context, callback: NavCallback) {
        ARouter.getInstance().build(url).navigation(context, callback)
    }

    fun goToActivityWithBundle(url: String, bundle: Bundle) {
        ARouter.getInstance().build(url).with(bundle).navigation()
    }

    fun goToActivityWithBundleTransition(url: String, bundle: Bundle, in1: Int, out1: Int) {
        ARouter.getInstance().build(url).with(bundle).withTransition(in1, out1).navigation()
    }

    fun goToActivityWithBundleCallBack(
        url: String,
        bundle: Bundle,
        context: Context,
        callback: NavCallback
    ) {
        ARouter.getInstance().build(url).with(bundle).navigation(context, callback)
    }

    fun goToActivityType(url: String, flag: Int) {
        ARouter.getInstance().build(url).withFlags(flag).navigation()
    }

    fun goToActivityBundleType(
        url: String,
        bundle: Bundle,
        flag: Int,
        context: Context,
        callback: NavCallback
    ) {
        ARouter.getInstance().build(url).with(bundle).withFlags(flag).navigation(context, callback)
    }
}
