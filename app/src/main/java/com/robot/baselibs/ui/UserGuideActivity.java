package com.robot.baselibs.ui;

import android.os.Bundle;

import com.robot.baselibs.base.activity.RobotBaseActivity;
import com.robot.baselibs.base.vm.RobotBaseViewModel;
import com.robot.baselibs.databinding.ActivityUserGuideBinding;
import com.robot.baselibs.model.BannerBizBean;

/**
 * 创建日期：2020/9/14  15:50
 * 类说明:
 *
 * @author：robot
 */
public class UserGuideActivity extends RobotBaseActivity<ActivityUserGuideBinding, RobotBaseViewModel> {

    private BannerBizBean mBannerBean;
//    private GuideViewAdapter mAdapter;


    @Override
    protected void initComponents() {

    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return 0;
    }

    @Override
    public int initVariableId() {
        return 0;
    }
}
