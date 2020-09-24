package com.robot.baselibs.vm;

import android.app.Application;

import androidx.annotation.NonNull;

import com.robot.baselibs.util.ActivityUtils;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class LoginViewModel extends BaseViewModel {

    public BindingCommand toRegisterPage = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
        }
    });
    public BindingCommand toForgetPasswordPage = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
        }
    });
    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

}
