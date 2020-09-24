package com.robot.baselibs.ui;

import android.os.Bundle;

import com.robot.baselibs.BR;
import com.robot.baselibs.R;
import com.robot.baselibs.base.activity.RobotBaseActivity;
import com.robot.baselibs.databinding.ActivityLoginBinding;
import com.robot.baselibs.vm.LoginViewModel;

import me.goldze.mvvmhabit.base.BaseViewModel;

public class LoginActivity extends RobotBaseActivity<ActivityLoginBinding, BaseViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void initComponents() {
        addSingleTitleBar("登录");
    }
}
