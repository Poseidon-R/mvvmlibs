package com.robot.baselibs.vm;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.robot.baselibs.base.vm.RobotBaseViewModel;
import com.robot.baselibs.configs.PrefsManager;
import com.robot.baselibs.model.SplashBannerBean;
import com.robot.baselibs.rx.AbstractViewModelSubscriber;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import api.CommonServiceFactory;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;

/**
 * 创建日期：2020/9/14  12:18
 * 类说明:
 *
 * @author：robot
 */
public class SplashViewModel extends RobotBaseViewModel {

    private Disposable disposable = null;

    private UILiveData ui;

    private SplashBannerBean mBannerBean;

    public SplashViewModel(@NonNull Application application) {
        super(application);
        disposable = Observable.timer(3000, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        splashAgreementDialog();
                    }
                });
        reqBanner();
    }

    private void reqBanner() {
        Map<String, Object> params = new HashMap<>();
        if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            params.put("onlyId", DeviceUtils.getAndroidID());
        } else {
            params.put("onlyId", PhoneUtils.getDeviceId());
        }
//        CommonServiceFactory.listAppSplash(params)
//                .subscribe(new AbstractViewModelSubscriber<BaseResponse<SplashBannerBean>>(this) {
//                    @Override
//                    public void onNext(BaseResponse<SplashBannerBean> splashBannerBeanBaseResponse) {
//                        mBannerBean = splashBannerBeanBaseResponse.getData();
//                        PrefsManager.saveSplashBanner(mBannerBean);
//                    }
//                });
    }

    /**
     * 启动启动页隐私弹框
     */
    private void splashAgreementDialog() {
//        if (ConfigInfoManager.getInstance().getAppSplashAgreement()) {
//            goNext();
//        } else {
//            getUI().getShowSplashAgreementDialog().postValue(null);
//        }
    }

    public void goNext() {
        if (mBannerBean == null) {
            mBannerBean = PrefsManager.getSplashBean();
        }
//        if (ConfigInfoManager.getInstance().getAppShowGuideView()) {
//            if (mBannerBean == null || mBannerBean.getSplashActivityVOList().size() == 0) {
//                UserGuideActivity.launch(SplashActivity.this, null);
//            } else {
//                UserGuideActivity.launch(this, mBannerBean.getSplashActivityVOList().get(0));
//            }
//        } else {
//            if (mBannerBean == null || mBannerBean.getSplashActivityVOList().size() == 0) {
//                MainActivity.launch(SplashActivity.this, null);
//            } else {
//                SplashBannerActivity.start(this, mBannerBean.getSplashActivityVOList().get(0));
//            }
//        }
        finish();
    }



    @Override
    public void onDestroy() {
        if (disposable != null && disposable.isDisposed()) {
            disposable.dispose();
        }
        disposable = null;
        super.onDestroy();
    }
    public UILiveData getUI() {
        if (ui == null) {
            ui = new UILiveData();
        }
        return ui;
    }

    public static class UILiveData {

        private SingleLiveEvent<Void> showSplashAgreementDialog;

        public SingleLiveEvent<Void> getShowSplashAgreementDialog() {
            return showSplashAgreementDialog = createLiveData(showSplashAgreementDialog);
        }
        private <T> SingleLiveEvent<T> createLiveData(SingleLiveEvent<T> liveData) {
            if (liveData == null) {
                liveData = new SingleLiveEvent<>();
            }
            return liveData;
        }

    }
}
