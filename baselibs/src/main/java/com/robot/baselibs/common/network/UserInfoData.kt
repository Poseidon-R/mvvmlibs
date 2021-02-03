package com.robot.baselibs.common.network

import com.blankj.utilcode.util.LogUtils
import com.robot.baselibs.model.PersonalCenterBean
import com.robot.baselibs.model.UserInfoBean
import com.robot.baselibs.util.ToastUtils
import org.clc.app.util.arouter.ARouterPath
import org.clc.app.util.arouter.ARouterUtil

/**
 * describe 用户信息
 * authors lvuchenLiu
 * createTime 2019/9/28 10:18
 *
 * modifier
 * endTime 2019/9/28 10:18
 */

class UserInfoData {

    var maxSelect = 8//足球、蓝球最大选择的数量为8

    var token = ""//token
    var simple_token = ""//短信token
    var socketToken = ""//socket token
    var avatar = ""//用户头像
    var buyPermissions = 0//购彩权限 1是有权限
    var vip_level = 0//vip等级
    var GoToMarkIcon = ""
    var GoToNews = ""
    var userInfoBean: UserInfoBean? = null//个人信息
    var personalCenterBean: PersonalCenterBean? = null//个人中心

    var gameSelectNum = 0//足球、篮球选择的场次数

    var isTab = 0//0是咨询模块，1是商城模块

    var gameid = ArrayList<String>()


    companion object {
        val instens: UserInfoData by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { UserInfoData() }
    }

    //是否是登录状态
    fun isLogin(): Boolean {
        return if (token.isEmpty()) {
            LogUtils.e("token失效")
            ARouterUtil.goToActivity(ARouterPath.LoginModuleAty)
            false
        } else {//登录状态
            true
        }
    }

    //足球、篮球最大选择场地为8
    fun isNext(id: String): Boolean {
        var isBoolean = false
        if (gameSelectNum < maxSelect) {
            isBoolean = true
        } else if (gameSelectNum == maxSelect) {
            for (s: String in gameid) {
                if (s == id) {
                    isBoolean = true
                }
            }
            if (!isBoolean) {
                ToastUtils.showShort("最多只能选${maxSelect}场比赛")
            }
        } else {
            ToastUtils.showShort("最多只能选${maxSelect}场比赛")
        }
        return isBoolean
    }

}
