package com.robot.baselibs.exception


/**
 * describe 网络请求的code
 * authors lvuchenLiu
 * createTime 2019/8/14 9:55
 *
 * modifier
 * endTime 2019/8/14 9:55
 */

object HttpCode {
    //token失效
    val TOKEN_OVERDUE1 = -125
    val TOKEN_OVERDUE2 = 403
    val UPDATE = -117

}

enum class ApiErrorType(var code: Int, var message: String) {
    //非业务错误
    INTERNAL_SERVER_ERROR(500, "服务器在开小差"),
    BAD_GATEWAY(502, "服务器好像丢失了"),
    NOT_FOUND(404, "您的数据去旅游了"),
    CONNECTION_TIMEOUT(408, "连接超时"),
    NETWORK_NOT_CONNECT(499, "网络逃跑了，请检查下网络"),
    UNEXPECTED_ERROR(700, "异常错误，请联系客服");
}