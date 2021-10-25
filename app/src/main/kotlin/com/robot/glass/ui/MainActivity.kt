package com.robot.glass.ui

import android.os.Bundle
import com.robot.glass.R
import com.robot.baselibs.base.activity.RobotBaseActivity
import com.robot.glass.databinding.ActivityMainBinding
import com.robot.glass.vm.MainViewModel
import me.goldze.mvvmhabit.BR


/**
 * 创建日期：2021/10/25  10:18
 * 类说明:
 * @author：86152
 */
class MainActivity : RobotBaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int = R.layout.activity_main

    override fun initVariableId(): Int = BR.viewModel

    override fun initComponents() {
        
    }
}