package com.robot.baselibs.util.debounce

import java.util.*

/**
 * describe 防抖动
 * authors lvuchenLiu
 * createTime 2019/10/25 11:15
 *
 * modifier
 * endTime 2019/10/25 11:15
 */
class AntiShake {
    private val utils = ArrayList<OneClickUtil>()

    @JvmOverloads
    fun check(o: Any? = null): Boolean {
        var flag: String? = o?.toString() ?: Thread.currentThread().stackTrace[2].methodName
        for (util in utils) {
            if (util.methodName == flag) {
                return util.check()
            }
        }
//        LogUtils.i("qqqq","flag:$flag")
        val clickUtil = OneClickUtil(flag!!)
        utils.add(clickUtil)
        return clickUtil.check()
    }

    inner class OneClickUtil(val methodName: String) {
        private var lastClickTime: Long = 0
        private val MIN_CLICK_DELAY_TIME = 1000
        fun check(): Boolean {
            val currentTime = Calendar.getInstance().timeInMillis
//            LogUtils.i("qqqq","$lastClickTime")
            return if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                lastClickTime = currentTime
                true
            } else {
                false
            }
        }
    }
}
