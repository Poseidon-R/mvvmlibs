package com.robot.baselibs.common.download

import com.robot.baselibs.util.LiveDataBus

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
class SkDownloadListenerImpl : SkDownloadListener {

    private val bus by lazy {
        LiveDataBus.get()
    }

    override fun onStartDownload() {
        bus.with(SkDownloadListenerImpl::class.java.canonicalName, DownloadInfo::class.java)
            .postValue(DownloadInfo(0, DownloadState.START))

    }

    override fun onProgress(progress: Int) {
        bus.with(SkDownloadListenerImpl::class.java.canonicalName, DownloadInfo::class.java)
            .postValue(DownloadInfo(progress, DownloadState.DOWNLOADING))
    }

    override fun onFinishDownload() {
        bus.with(SkDownloadListenerImpl::class.java.canonicalName, DownloadInfo::class.java)
            .postValue(DownloadInfo(100, DownloadState.FINISH))
    }

    override fun onFail(errorInfo: String) {
        bus.with(SkDownloadListenerImpl::class.java.canonicalName, DownloadInfo::class.java)
            .postValue(DownloadInfo(0, DownloadState.FAILED))
    }


}