package com.robot.baselibs.model

/**
 * <pre>
 *     e-mail : junfeng.nie@duntech.com.cn
 *     time   : 2020-04-17
 *     desc   :
 *     version: org.clc.app.bean
 *     Copyright: Copyright（c）2018
 *     Company:
 * </pre>
 * @author niejunfeng
 */
class VerisonBean {
    var head: String? = ""//当前发布版本1默认为0

    var id: String? = ""//integer($int32)
    var info: String? = ""//	stringexample: <h2>hello world!</h2>版本更新详情

    var mandatory: String? = ""//	integer($int32)是否强制0不强制，1强制更新（默认0）

    var platform: String? = ""//平台(1苹果，0安卓)

    var remark: String? = ""//	stringexample: 发布新版本备注

    var url: String? = ""//*	string下载地址

    var versionCode: String? = ""//*	string版本号
}