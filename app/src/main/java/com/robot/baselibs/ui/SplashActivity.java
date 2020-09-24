package com.robot.baselibs.ui;

import android.os.Bundle;

import androidx.lifecycle.Observer;

import com.robot.baselibs.R;
import com.robot.baselibs.base.activity.RobotBaseActivity;
import com.robot.baselibs.configs.ConfigInfoManager;
import com.robot.baselibs.databinding.ActivitySplashBinding;
import com.robot.baselibs.ui.web.PureWebActivity;
import com.robot.baselibs.util.ActivityUtils;
import com.robot.baselibs.vm.SplashViewModel;

import me.goldze.mvvmhabit.BR;

/**
 * 创建日期：2020/9/14  11:50
 * 类说明:
 *
 * @author：robot
 */
public class SplashActivity extends RobotBaseActivity<ActivitySplashBinding, SplashViewModel> {
    @Override
    protected void initComponents() {
        viewModel.getUI().getShowSplashAgreementDialog().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                showSplashAgreementDialog();
            }
        });
    }

    private void showSplashAgreementDialog(){

    }
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_splash;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }
}
