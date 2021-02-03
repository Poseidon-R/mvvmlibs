package com.robot.baselibs.common.network

import androidx.collection.ArrayMap
import io.reactivex.observers.DisposableObserver

/**
 * describe 网络请求tag管理
 * 相同tag的请求只会存在一个
 * authors lvuchenLiu
 * createTime 2019/8/14 10:02
 *
 * modifier
 * endTime 2019/8/14 10:02
 */
class ApiTagManager private constructor() {

    private val maps = ArrayMap<String, DisposableObserver<*>>()

    companion object {
        val instance = ApiTagManager()
    }

    /**
     * 后入队的会被关闭
     */
    fun add(tag: String, observer: DisposableObserver<*>) {
        maps.keys.forEach {
            if (tag == it) {
                observer.dispose()
                return
            }
        }
        maps[tag] = observer
    }

    /**
     * 先入队的会被关闭
     */
    fun add2(tag: String, observer: DisposableObserver<*>) {
        maps.keys.forEach {
            if (tag == it) {
                maps[it]?.dispose()
                maps.remove(it)
                maps[tag] = observer
                return
            }
        }
        maps.put(tag, observer)
    }

    fun remove(tag: String) {
        if (!maps.isEmpty) {
            maps.remove(tag)
        }
    }

    fun removeAll() {
        if (!maps.isEmpty) {
            maps.clear()
        }
    }

    fun cancel(tag: String) {
        if (maps.isEmpty) {
            return
        }
        val m1 = maps[tag] ?: return
        if (!m1.isDisposed) {
            m1.dispose()
            maps.remove(tag)
        }
    }

    fun cancelAll() {
        if (maps.isEmpty) {
            return
        }
        val tags = maps.keys
        for (tag in tags) {
            cancel(tag)
        }
    }
}