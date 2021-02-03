package com.robot.baselibs.ui

import android.os.Bundle
import com.robot.baselibs.R
import com.robot.baselibs.base.activity.RobotBaseActivity
import com.robot.baselibs.databinding.ActivityRegisterBinding
import com.robot.baselibs.vm.RegisterViewModel
import me.goldze.mvvmhabit.BR


/**
 * 创建日期：2021/2/3  14:14
 * 类说明:
 * @author：86152
 */
class RegisterActivity :RobotBaseActivity<ActivityRegisterBinding,RegisterViewModel>() {
    override fun initComponents() {
        addSingleTitleBar("注册")
    }

    override fun initVariableId(): Int = BR.viewModel

    override fun initContentView(savedInstanceState: Bundle?): Int = R.layout.activity_register
}