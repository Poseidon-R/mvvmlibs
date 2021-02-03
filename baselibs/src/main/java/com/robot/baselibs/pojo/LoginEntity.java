package com.robot.baselibs.pojo;

import java.io.Serializable;

/**
 * 创建日期：2021/2/3  16:08
 * 类说明:
 *
 * @author：86152
 */
public class LoginEntity implements Serializable {

    private String token ;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
