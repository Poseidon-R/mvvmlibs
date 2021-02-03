package com.robot.baselibs.common.download

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


/**
 * <pre>
 *     e-mail : 18721411287@163.com
 *     time   : 2018/7/10
 *     desc   :
 *     version: 1.0
 *     Copyright: Copyright（c）2017
 *     Company:
 * </pre>
 * @author niejunfeng
 */
class RxManager() {

    private val onNextStub: (Any) -> Unit = {}
    private val onErrorStub: (Throwable) -> Unit = { }
    private val onCompleteStub: () -> Unit = {}
    private val task = arrayListOf<Disposable>()

    private var mCompositeDisposable: CompositeDisposable? = CompositeDisposable()

    fun <T : Any> sendHttpRequest(
        request: Observable<T>,
        onSuccess: (T) -> Unit = onNextStub,
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub
    ) {

        val disposable = request.subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSuccess, onError, onComplete)
        task.add(disposable)
        mCompositeDisposable?.add(disposable)
    }

    fun <T : Any> sendIntervalHttpRequest(
        request: Observable<T>,
        onSuccess: (T) -> Unit = onNextStub,
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub
    ) {
        request.timeInterval()
        val disposable = request.subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSuccess, onError, onComplete)
        mCompositeDisposable?.add(disposable)
    }

    fun shutdown() {
        Log.d("RxManager{}", "shutdown")
        Log.d("RxManager{}", mCompositeDisposable?.size().toString())
        task.map {
            mCompositeDisposable?.remove(it)
        }
    }

    fun clear() {
        Log.d("RxManager{}", "clear")
        Log.d("RxManager{}", mCompositeDisposable?.size().toString())
        mCompositeDisposable?.dispose()

        mCompositeDisposable?.clear()

        mCompositeDisposable = null
    }
}