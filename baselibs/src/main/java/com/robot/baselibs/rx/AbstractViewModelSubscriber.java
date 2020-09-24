package com.robot.baselibs.rx;

import com.robot.baselibs.base.vm.RobotBaseViewModel;
import com.robot.baselibs.util.ToastUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author Dankal Android Developer
 * @since 2018/7/18
 */

public abstract class AbstractViewModelSubscriber<T> implements Observer<T> {

    protected RobotBaseViewModel view;

    public AbstractViewModelSubscriber(RobotBaseViewModel view) {
        this.view = view;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (view != null) {
            view.addNetworkRequest(d);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (e == null || view == null) {
            return;
        }
        view.dismissLoadingDialog();
        ToastUtils.showShort(e.getMessage());
//        if (e instanceof LocalException) {
//            LocalException exception = (LocalException) e;
//            //401 重新获取access token , 如果还返回412 就是refresh token 也失效了。需要重新登录
//            if (exception.getErrorCode() == HttpStatusCode.TOKEN_INVAILD ||
//                    exception.getErrorCode() == HttpStatusCode.UNAUTHORIZED) {
////                view.tokenInvalid();
//                //view.showToast("登录信息失效");
//                try {
//                    QHUserManager.exit();
//                    ActivityUtils.startActivity(Class.forName(QHApplication.getContext().getString(R.string.login_activity_path)));
//                } catch (ClassNotFoundException e1) {
//                    e1.printStackTrace();
//                }
//            } else {
//                ToastUtils.showShort(exception.getMsg());
////                view.showToast(exception.getMsg());
//            }
//        } else {
//            Log.e("SubscriberThrowable", e.getMessage());
//        }
    }

    @Override
    public void onComplete() {
        this.view = null;
    }
}
