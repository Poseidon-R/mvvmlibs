package com.robot.baselibs.common.cookie

import android.annotation.SuppressLint
import android.content.Context
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl


/**
 * describe RetrofitClient  Cookie管理
 * authors lvuchenLiu
 * createTime 2019/8/13 18:23
 *
 * modifier
 * endTime 2019/8/13 18:23
 */

class NovateCookieManger
/**
 * Mandatory constructor for the NovateCookieManger
 */
internal constructor(context: Context) : CookieJar {

    init {
        //        mContext = context;
        if (cookieStore == null) {
            cookieStore = PersistentCookieStore(context)
        }
    }

    //根绝cookie处理策略，保存响应中的cookie
    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        if (cookies.size > 0) {
            for (item in cookies) {
                cookieStore!!.add(url, item)
            }
        }
    }

    //为请求添加cookie
    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return cookieStore!!.get(url)
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        private//    private static Context mContext;
        var cookieStore: PersistentCookieStore? = null
    }

}
