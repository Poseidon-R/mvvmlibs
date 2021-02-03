package com.robot.baselibs.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import com.robot.baselibs.R
import com.robot.baselibs.base.activity.RobotBaseActivity
import com.robot.baselibs.databinding.ActivitySplashBinding
import com.robot.baselibs.vm.SplashViewModel
import me.goldze.mvvmhabit.BR

/**
 * 创建日期：2020/9/14  11:50
 * 类说明:
 *
 * @author：robot
 */
class SplashActivity : RobotBaseActivity<ActivitySplashBinding?, SplashViewModel?>() {
    override fun initComponents() {
        viewModel!!.ui.showSplashAgreementDialog.observe(this, Observer { showSplashAgreementDialog() })
    }

    private fun showSplashAgreementDialog() {}
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_splash
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel!!.onDestroy()
    }

}