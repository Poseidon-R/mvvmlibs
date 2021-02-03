package com.robot.baselibs.model

import com.robot.baselibs.common.network.NetWorkBean

/**
 * @program: fcj
 * @description: ${description}
 * @author: zk
 * @create: 2019-08-15 11:57
 */
class UserInfoBean : NetWorkBean() {

    /**
     * data1 : {"realName":null,"rid":47,"uname":"kaka","idCard":null,"avatar":"lecai-images.oss-cn-hangzhou.aliyuncs.com/#","uMobile":"18797810527"}
     */

    var data: DataBean? = null

    class DataBean {
        /**
         * 个人信息
         * realName : null
         * rid : 47
         * uname : kaka
         * idCard : null
         * avatar : lecai-images.oss-cn-hangzhou.aliyuncs.com/#
         * uMobile : 18797810527
         */

        var realName: String? = null
        var uid: Int = 0
        var uname: String? = null
        var avatar: String? = null
        var uMobile: String? = null
        var idCard: String? = null


    }
}