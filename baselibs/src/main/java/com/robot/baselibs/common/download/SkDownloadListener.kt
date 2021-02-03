package com.robot.baselibs.common.download

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
interface SkDownloadListener {
    fun onStartDownload()

    fun onProgress(progress: Int)

    fun onFinishDownload()

    fun onFail(errorInfo: String)
}