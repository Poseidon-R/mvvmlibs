package com.robot.baselibs.ui

import android.os.Bundle
import com.robot.baselibs.R
import com.robot.baselibs.base.activity.RobotBaseActivity
import com.robot.baselibs.databinding.ActivityLoginBinding
import com.robot.baselibs.vm.LoginViewModel
import me.goldze.mvvmhabit.BR


/**
 * 创建日期：2021/2/3  14:33
 * 类说明:
 * @author：86152
 */
class LoginActivity : RobotBaseActivity<ActivityLoginBinding,LoginViewModel>() {
    override fun initComponents() {

    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initContentView(savedInstanceState: Bundle?): Int = R.layout.activity_login
}