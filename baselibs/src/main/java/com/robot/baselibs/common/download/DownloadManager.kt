package com.robot.baselibs.common.download

import com.blankj.utilcode.util.LogUtils
import com.robot.baselibs.common.domain.HttpLoggingInterceptor
import com.robot.baselibs.common.network.RetrofitClient
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.*
import java.util.concurrent.TimeUnit

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
class DownloadManager {

    private val downloadListener = SkDownloadListenerImpl()

    fun startDownload(downloadUrl: String, filePath: String) {
        downloadListener.onStartDownload()
        RetrofitClient.instance.downApk(downloadUrl)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map {
                    return@map it.byteStream()
                }
                .observeOn(Schedulers.computation())
                .doOnNext {
                    LogUtils.e(it, filePath)
                    writeFile(it, filePath)
                    downloadListener.onFinishDownload()
                }
                .doOnError {
                    downloadListener.onFail(it.message ?: "未知异常")
                }
                .subscribe()
    }

    private fun createRestClient(baseUrl: String): Retrofit {
        val logging = HttpLoggingInterceptor()
        val okHttpClient = OkHttpClient().newBuilder()
                .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(logging)
                .addInterceptor(SkDownloadInterceptor(SkDownloadListenerImpl()))
                .build()

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    /**
     * 将输入流写入文件
     *
     * @param inputString
     * @param filePath
     */
    private fun writeFile(inputString: InputStream, filePath: String) {

        val file = File(filePath)
        if (file.exists()) {
            file.delete()
        }

        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(file)

            val b = ByteArray(1024)

            var len = inputString.read(b)
            while (len != -1) {
                fos.write(b, 0, len)
                len = inputString.read(b)
            }
            inputString.close()
            fos.close()

        } catch (e: FileNotFoundException) {
            if (fos != null)
                fos.close()
            downloadListener.onFail("FileNotFoundException")
        } catch (e: IOException) {
            if (fos != null)
                fos.close()
            downloadListener.onFail("IOException")
        } catch (e: Exception) {
            if (fos != null)
                fos.close()
            downloadListener.onFail("下载超时")
        }

    }

    companion object {
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

        fun getInstance(): DownloadManager {
            return DownloadManager()
        }
    }

}