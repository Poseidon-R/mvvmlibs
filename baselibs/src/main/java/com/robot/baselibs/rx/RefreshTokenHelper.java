package com.robot.baselibs.rx;

import com.robot.baselibs.exception.ExceptionHandle;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * @author Dankal Android Develoer
 * @since 2018/7/18
 */

public class RefreshTokenHelper {

    public static <T> Function<Throwable, ObservableSource<T>> refreshTokenFunc(final Observable<T> observable) {
        return new Function<Throwable, ObservableSource<T>>() {
            @Override
            public ObservableSource<T> apply(Throwable throwable) throws Exception {
               /* if (throwable instanceof HttpException) {
                    HttpException httpException = (HttpException) throwable;
                    if (HttpStatusCode.UNAUTHORIZED == httpException.code()) {
                        return FgUserServiceFactory.updateToken(DKUserManager.getUserToken().getRefreshToken())
                                .observeOn(Schedulers.io())
                                .flatMap(tokenBean -> {
                                    DKUserManager.updateUserToken(tokenBean);
                                    return observable;

                                });
                    }
                }*/
                return Observable.error(ExceptionHandle.handleException(throwable));
            }
        };
    }


}
