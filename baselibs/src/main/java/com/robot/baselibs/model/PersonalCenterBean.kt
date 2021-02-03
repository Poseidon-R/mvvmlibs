package com.robot.baselibs.model

import com.robot.baselibs.common.network.NetWorkBean


/**
 * describe 个人中心
 * authors lvuchenLiu
 * createTime 2019/9/28 15:21
 *
 * modifier
 * endTime 2019/9/28 15:21
 */

class PersonalCenterBean : NetWorkBean() {

    /**
     * data1 : {"realName":null,"rid":47,"uname":"kaka","idCard":null,"avatar":"lecai-images.oss-cn-hangzhou.aliyuncs.com/#","uMobile":"18797810527"}
     */

    var data: DataBean? = null

    class DataBean {
        /**
         * 个人中心
         * handsel : 1103
         * buyPermissions : 0
         * nonCashCapital : 1273100
         * idCard : null
         * activeCapital : 0
         * vip : 0
         */

        var handsel: Double = 0.0//乐乐币
        var buyPermissions: Int = 0
        var nonCashCapital: Double = 0.0//充值金额
        var idCard: String? = null//证件号码
        var activeCapital: Double = 0.0//收入资金
        var vip: Int = 0
        var defaultRole: Int = 0//默认角色id
        var uid: Int = 0
        var avatar: String? = null//头像
        var uname: String? = null//昵称

    }
}
