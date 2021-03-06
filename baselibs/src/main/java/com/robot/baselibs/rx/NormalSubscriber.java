package com.robot.baselibs.rx;

import android.util.Log;

import com.robot.baselibs.R;
import com.robot.baselibs.App;
import com.robot.baselibs.common.domain.HttpStatusCode;
import com.robot.baselibs.exception.LocalException;
import com.robot.baselibs.util.ActivityUtils;

import io.reactivex.Observer;

//import com.hyphenate.chat.EMClient;

/**
 * @author Dankal Android Developer
 * @since 2018/7/18
 */

public abstract class NormalSubscriber<T> implements Observer<T> {

    @Override
    public void onError(Throwable e) {
        if (e instanceof LocalException) {
            LocalException exception = (LocalException) e;
            //401 重新获取access token , 如果还返回412 就是refresh token 也失效了。需要重新登录
            if (exception.getErrorCode() == HttpStatusCode.TOKEN_INVAILD
                    ||exception.getErrorCode() == HttpStatusCode.UNAUTHORIZED) {
//                EMClient.getInstance().logout(true);
//                DKUserManager.resetToken();
//                DKUserManager.resetUserInfo();
                ActivityUtils.finishAllActivities();
                try {
                    ActivityUtils.startActivity(Class.forName(App.getContext().getString(R.string.login_activity_path)));
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            } else {
            }
        } else {
            Log.e("SubscriberThrowable", e.getMessage());
        }
    }

    @Override
    public void onComplete() {
    }
}
