package com.robot.baselibs.common.download

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.robot.baselibs.common.network.NetWorkUrl
import com.robot.baselibs.common.network.RetrofitClient
import com.robot.baselibs.model.BaseDataBean
import com.robot.baselibs.model.VerisonBean
import com.tbruyelle.rxpermissions2.RxPermissions
import java.io.File


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
class VersionManager() {

    private var localVersionCode = 0

    private var localVersionShow = ""

    private var remoteVersionCode = 0

    private var updateContent = "您有新版本！是否需要更新!"

    private var isForce = false

    private var newVersionDesc = ""

    private var newVersionName = ""

    private var downloadApkUrl = ""

    private var downloadDirectory: File

    var downLoadApkName = "lkt.apk"


    private var rxManager: RxManager = RxManager()


    //5-1     6-29

    //---5-31 6.29
    init {
        localVersionCode = AppUtils.getAppVersionCode()
        downloadDirectory = File(Environment.getExternalStorageDirectory(), downLoadApkName)
    }

    fun hasNewVersion() = remoteVersionCode > localVersionCode

    fun shouldForceUpdate() = isForce

    fun remoteVersionCode() = remoteVersionCode

    fun remoteVersionName() = newVersionName

    fun remoteVersionDesc() = newVersionDesc

    fun localVersionShow() = AppUtils.getAppVersionName()

    fun fetchRemoteVersionInfo(updateComplete: () -> Unit) {
        rxManager.sendHttpRequest(RetrofitClient.instance.getHead(
            0,
            NetWorkUrl.CHECK_APP_VERSION,
            AppUtils.getAppVersionName()
        ),
            onSuccess = {
                val gson = Gson()
                val data =
                    gson.fromJson<BaseDataBean<VerisonBean>>(
                        it,
                        object : TypeToken<BaseDataBean<VerisonBean>>() {}.type
                    )

                data.data.apply {
                    if (this == null) return@apply
                    remoteVersionCode = (this.versionCode?.replace(".", "") ?: "0").toInt()
                    newVersionDesc = this.info ?: ""
                    downloadApkUrl = this.url ?: ""
                    isForce = StringUtils.equalsIgnoreCase(this.mandatory, "1")
                }
                updateComplete()
            },
            onError = {
                updateComplete()
            }
        )
    }

    fun downloadNewVersion(activity: AppCompatActivity) {
        if (StringUtils.isEmpty(downloadApkUrl)) {
            ToastUtils.showShort("无版本下载地址")
            return
        }
        RxPermissions(activity)
            .requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .subscribe { permission ->
                if (permission.granted) {
                    val downloadManager = DownloadManager.getInstance()
                    LogUtils.e(downloadApkUrl, downloadDirectory.absolutePath)
                    downloadManager.startDownload(downloadApkUrl, downloadDirectory.absolutePath)
                } else if (permission.shouldShowRequestPermissionRationale) {
                    ToastUtils.showShort("请授权再继续操作！")
                } else {
                    openAppSettings()
                }
            }

    }

    /**
     * 打开应用具体设置
     */
    fun openAppSettings() {
        val intent = Intent("android.settings.APPLICATION_DETAILS_SETTINGS")
        intent.data = Uri.parse("package:" + Utils.getApp().packageName)
        Utils.getApp().startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }


    companion object {

        /**
         * 版本更新测试接口地址
         */
//        private const val BASE_URL = "http://hd.amso.com.cn"
        /**
         * 连接超时 15秒
         */
        private const val CONNECTION_TIME_OUT = 15L
        /**
         * 读取超时15秒
         */
        private const val READ_TIME_OUT = 15L
        /**
         * 写出超时15秒
         */
        private const val WRITE_TIME_OUT = 15L

        private var versionManager: VersionManager? = null

        @JvmStatic
        fun getInstance(): VersionManager {

            if (versionManager == null) {
                synchronized(VersionManager::class.java) {
                    if (versionManager == null) {
                        versionManager = VersionManager()
                    }
                }
            }
            return versionManager!!
        }

    }
}