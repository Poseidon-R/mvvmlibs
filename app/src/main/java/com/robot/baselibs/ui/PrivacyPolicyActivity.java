package com.robot.baselibs.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.robot.baselibs.R;
import com.robot.baselibs.base.activity.RobotBaseActivity;
import com.robot.baselibs.base.vm.RobotBaseViewModel;
import com.robot.baselibs.databinding.ActivityPrivacyPolicyBinding;
import com.robot.baselibs.view.titlebar.ITitleBar;
import com.robot.baselibs.view.titlebar.NormalTitleBar;
import com.robot.baselibs.view.titlebar.SingleTextTitle;

import me.goldze.mvvmhabit.BR;


/**
 * on 2019/9/12 12:03
 * describe: 隐私政策
 * @author robot
 */
public class PrivacyPolicyActivity extends RobotBaseActivity<ActivityPrivacyPolicyBinding, RobotBaseViewModel> {

    private NormalTitleBar titleBar;

    @Override
    protected void initComponents() {
//        addTitleBar(new SingleTextTitle("隐私"));
        titleBar = new NormalTitleBar();
        addTitleBar(titleBar);
        titleBar.setTitleText(getResources().getString(R.string.about_us_privacy_explain));
        titleBar.setLeftImageSrc(R.drawable.ic_black_back);
        titleBar.setOnLeftImageListener(v -> finish());
        titleBar.setRightCommonTool(this, NormalTitleBar.LIGHT_MODE);
        binding.web.loadUrl("file:///android_asset/PrivacyPolicy.htm");
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_privacy_policy;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
