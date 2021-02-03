package com.robot.baselibs.common.network

import android.content.Context
import android.net.ConnectivityManager
import com.blankj.utilcode.util.ActivityUtils
import com.robot.baselibs.view.ContentDialog
import java.util.regex.Pattern

/**
 * describe 网络管理工具
 * authors lvuchenLiu
 * createTime 2019/8/14 10:08
 *
 * modifier
 * endTime 2019/8/14 10:08
 */

object NetWorkUtils {

    //    var contentDialog: ContentDialog? = null
    fun showDialog(content: String) {
//        if (contentDialog == null) {
        val contentDialog = ContentDialog.Builder(ActivityUtils.getTopActivity())
            .withContentText(content)
            .onPositive {
                it.dismiss()
            }
            .builder()
//        }
//        if (contentDialog != null) {
        if (!contentDialog.isShowing) {
            contentDialog.show()
        }
//        }
    }
    /**
     * 检查网络是否可用
     *
     * @param paramContext
     * @return
     */
    fun isNetConnected(paramContext: Context): Boolean {
        val i = false
        val localNetworkInfo = (paramContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
        return localNetworkInfo != null && localNetworkInfo.isAvailable
    }

    /**
     * 检测wifi是否连接
     */
    fun isWifiConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (cm != null) {
            val networkInfo = cm.activeNetworkInfo
            return networkInfo != null && networkInfo.type == ConnectivityManager.TYPE_WIFI
        }
        return false
    }

    /**
     * 检测3G是否连接
     */
    fun is3gConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (cm != null) {
            val networkInfo = cm.activeNetworkInfo
            return networkInfo != null && networkInfo.type == ConnectivityManager.TYPE_MOBILE
        }
        return false
    }

    /**
     * 判断网址是否有效
     */
    fun isLinkAvailable(link: String): Boolean {
        val pattern = Pattern.compile(
            "^(http://|https://)?((?:[A-Za-z0-9]+-[A-Za-z0-9]+|[A-Za-z0-9]+)\\.)+([A-Za-z]+)[/\\?\\:]?.*$",
            Pattern.CASE_INSENSITIVE
        )
        val matcher = pattern.matcher(link)
        return matcher.matches()
    }
}
