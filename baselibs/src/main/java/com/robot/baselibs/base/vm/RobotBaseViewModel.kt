package com.robot.baselibs.base.vm

import android.app.Application
import com.robot.baselibs.util.ActivityUtils
import com.robot.baselibs.view.TipDialog
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import me.goldze.mvvmhabit.base.BaseModel
import me.goldze.mvvmhabit.base.BaseViewModel

/**
 * 创建日期：2020/9/14  12:18
 * 类说明:
 *
 * @author：robot
 */
open class RobotBaseViewModel(application: Application) : BaseViewModel<BaseModel>(application) {
    private val mDisposables: CompositeDisposable? = CompositeDisposable()
    protected var loadingDialog: TipDialog? = null
    fun addNetworkRequest(d: Disposable?) {
        mDisposables!!.add(d!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposables?.clear()
    }

    fun showLoadingDialog() {
        if (loadingDialog != null) {
            loadingDialog!!.dismiss()
            loadingDialog = null
        }
        loadingDialog = TipDialog.Builder(ActivityUtils.getTopActivity())
                .setIconType(TipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create()
        loadingDialog?.show()
    }

    fun dismissLoadingDialog() {
        if (loadingDialog != null && loadingDialog!!.isShowing) {
            loadingDialog!!.dismiss()
        }
    }
}