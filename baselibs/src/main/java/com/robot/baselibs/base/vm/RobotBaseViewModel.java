package com.robot.baselibs.base.vm;

import android.app.Application;

import androidx.annotation.NonNull;

import com.robot.baselibs.util.ActivityUtils;
import com.robot.baselibs.view.TipDialog;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * 创建日期：2020/9/14  12:18
 * 类说明:
 *
 * @author：robot
 */
public class RobotBaseViewModel extends BaseViewModel {

    private CompositeDisposable mDisposables = new CompositeDisposable();
    protected TipDialog loadingDialog;


    public void addNetworkRequest(Disposable d) {
        mDisposables.add(d);
    }

    public RobotBaseViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposables != null) mDisposables.clear();
    }

    public void showLoadingDialog() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
        loadingDialog = new TipDialog.Builder(ActivityUtils.getTopActivity())
                .setIconType(TipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create();
        loadingDialog.show();
    }

    public void dismissLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

}
