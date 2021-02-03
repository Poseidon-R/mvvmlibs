package com.robot.baselibs.util

import android.graphics.Bitmap
import android.os.Environment
import android.util.Log
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.LogUtils
import com.robot.baselibs.common.download.DownloadInfo
import com.robot.baselibs.common.download.DownloadState
import com.robot.baselibs.common.download.SkDownloadListenerImpl
import com.robot.baselibs.common.download.VersionManager
import com.robot.baselibs.view.MaterialDesignDialog
import me.goldze.mvvmhabit.base.BaseActivity
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

object Util {
    private const val TAG = "SDK_Sample.Util"
    fun bmpToByteArray(bmp: Bitmap, needRecycle: Boolean): ByteArray {
        val output = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output)
        if (needRecycle) {
            bmp.recycle()
        }
        val result = output.toByteArray()
        try {
            output.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    fun getHtmlByteArray(url: String?): ByteArray? {
        var htmlUrl: URL? = null
        var inStream: InputStream? = null
        try {
            htmlUrl = URL(url)
            val connection = htmlUrl.openConnection()
            val httpConnection = connection as HttpURLConnection
            val responseCode = httpConnection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inStream = httpConnection.inputStream
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return inputStreamToByte(inStream)
    }

    fun inputStreamToByte(`is`: InputStream?): ByteArray? {
        try {
            val bytestream = ByteArrayOutputStream()
            var ch: Int
            while (`is`!!.read().also { ch = it } != -1) {
                bytestream.write(ch)
            }
            val imgdata = bytestream.toByteArray()
            bytestream.close()
            return imgdata
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun readFromFile(fileName: String?, offset: Int, len: Int): ByteArray? {
        var len = len
        if (fileName == null) {
            return null
        }
        val file = File(fileName)
        if (!file.exists()) {
            Log.i(TAG, "readFromFile: file not found")
            return null
        }
        if (len == -1) {
            len = file.length().toInt()
        }
        Log.d(TAG, "readFromFile : offset = " + offset + " len = " + len + " offset + len = " + (offset + len))
        if (offset < 0) {
            Log.e(TAG, "readFromFile invalid offset:$offset")
            return null
        }
        if (len <= 0) {
            Log.e(TAG, "readFromFile invalid len:$len")
            return null
        }
        if (offset + len > file.length().toInt()) {
            Log.e(TAG, "readFromFile invalid file len:" + file.length())
            return null
        }
        var b: ByteArray? = null
        try {
            val `in` = RandomAccessFile(fileName, "r")
            b = ByteArray(len)
            `in`.seek(offset.toLong())
            `in`.readFully(b)
            `in`.close()
        } catch (e: Exception) {
            Log.e(TAG, "readFromFile : errMsg = " + e.message)
            e.printStackTrace()
        }
        return b
    }

    private const val MAX_DECODE_PICTURE_SIZE = 1920 * 1440

    private var dialog: MaterialDesignDialog? = null

    private val versionManager = VersionManager.getInstance()
    /**
     * 是否需要更新
     *
     */
    public fun checkVersion() {
        versionManager.fetchRemoteVersionInfo {
            if (versionManager.hasNewVersion()) {
                val isForce = versionManager.shouldForceUpdate()
                if (dialog == null) {
                    dialog = showVersionUpdate(isForce, versionManager.remoteVersionDesc())
                }
                if (!dialog!!.isAdded) {
                    //解决方法就是添加这行代码，如果已经添加了，就移除掉然后再show，就不会出现Fragment already added的错误了。
//                    (ActivityUtils.getTopActivity() as BaseActivity).supportFragmentManager.beginTransaction()
//////                        .remove(
//////                            dialog!!
//////                        ).commit()
                    dialog!!.show(
                            (ActivityUtils.getTopActivity() as BaseActivity<*,*>)
                                    .supportFragmentManager,
                            ""
                    )
                }
            }
        }
    }

    private fun showVersionUpdate(isForce: Boolean, content: String): MaterialDesignDialog {
        val dialog = MaterialDesignDialog.Builder(ActivityUtils.getTopActivity())
                .withTitle("提示")
                .withContent(content)
                .withNegativeText("")
                .setIsCancelable(false)
                .withPositiveText("立即更新")
                .onPositive { dialog ->
                    dialog.setDialogModel(MaterialDesignDialog.Model.PROGRESS_PROGRESS)
                    dialog.setProgress(0f)
                    doDownload()
                }
                .onNegative { dialog!!.dismiss() }
        if (!isForce) {
            dialog.withNegativeText("跳过")

        }
        return dialog.builder()
    }

    private fun doDownload() {
        versionManager.downloadNewVersion(ActivityUtils.getTopActivity() as BaseActivity<*,*>)
        LiveDataBus.get()
                .with(SkDownloadListenerImpl::class.java.canonicalName, DownloadInfo::class.java)
                .observe(ActivityUtils.getTopActivity() as BaseActivity<*,*>, Observer {
                    when (it.state) {
                        DownloadState.START -> {

                        }
                        DownloadState.DOWNLOADING -> {
                            dialog!!.setProgress(it.progress.toFloat())
                        }
                        DownloadState.FINISH -> {
                            autoInstallApk()
                            dialog!!.dismiss()
                        }
                    }
                })
    }

    private fun autoInstallApk() {
        LogUtils.e("自动安装新版Apk{}")
        val saveFilePath =
                File(Environment.getExternalStorageDirectory(), versionManager.downLoadApkName)
        // "com.duntech.mbooking.fileprovider"
        LogUtils.e(saveFilePath)
        AppUtils.installApp(saveFilePath)
        ActivityUtils.finishAllActivities()
    }
}