package com.robot.baselibs.vm

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import api.UserServiceFactory
import com.blankj.utilcode.util.StringUtils
import com.robot.baselibs.base.vm.RobotBaseViewModel
import com.robot.baselibs.model.BaseResponse
import com.robot.baselibs.pojo.LoginEntity
import com.robot.baselibs.rx.AbstractViewModelSubscriber
//import com.robot.baselibs.ui.RegisterActivity
import com.robot.baselibs.util.ActivityUtils
import com.robot.baselibs.util.ToastUtils
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand


/**
 * 创建日期：2021/2/3  14:32
 * 类说明:
 * @author：86152
 */
class LoginViewModel(application: Application) : RobotBaseViewModel(application) {

    var username = ObservableField<String>()

    var password = ObservableField<String>()


    var toRegister = BindingCommand<Any>(BindingAction {
//        ActivityUtils.startActivity(RegisterActivity::class.java)
    })
    var doLogin = BindingCommand<Any>(BindingAction {
        loginReq()
    })

    private fun loginReq() {
        val usernameParams = username.get() ?: ""
        val passwordParams = password.get() ?: ""
        if (StringUtils.isEmpty(usernameParams)) {
            ToastUtils.showShort("请输入用户名")
            return
        }
        if (StringUtils.isEmpty(passwordParams)) {
            ToastUtils.showShort("请输入密码")
            return
        }
        val params = mutableMapOf<String, Any>()
        params.put("account", usernameParams)
        params.put("pwd", passwordParams)
        showLoadingDialog()
        UserServiceFactory.login(params)
                .subscribe(object : AbstractViewModelSubscriber<BaseResponse<LoginEntity>>(this) {
                    override fun onNext(t: BaseResponse<LoginEntity>) {
                        dismissLoadingDialog()
                        ToastUtils.showShort("登录成功")
                    }
                })
    }
}