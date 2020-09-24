package com.robot.baselibs.model;

import java.io.Serializable;


public class UserLoginInfoEntity implements Serializable {

    /**
     * birthMonth : null
     * birthDay : null
     * uid : 8tfialpp
     * birthYear : null
     * sex : 0
     * nickname :
     * mobile : 15158121565
     * client : 2
     * avatar :
     * exp : Fri Jun 14 16:11:32 CST 2019
     * token : eyJhbGciOiJIUzUxMiJ9.eyJ1aWQiOiI4dGZpYWxwcCIsImNsaWVudCI6MiwiZXhwIjoxNTYxNzk1ODkyfQ.hCV8Q-z_8iXVgUnqheHanPUS0L49A-CXD38nJt-rdLJma0woARx5gyeTcefuEmzX6aiPYUrvlUxA7MCsmnW3ew
     */

    private String birthMonth;
    private String birthDay;
    private String uid;
    private String birthYear;
    private double sex;
    private String nickname;
    private String mobile;
    private int client;
    private String avatar;
    private String exp;
    private String token;
    private String openId;
    private int currentLoginGetIntegral;

    public String getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(String birthMonth) {
        this.birthMonth = birthMonth;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public double getSex() {
        return sex;
    }

    public void setSex(double sex) {
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public int getCurrentLoginGetIntegral() {
        return currentLoginGetIntegral;
    }

    public void setCurrentLoginGetIntegral(int currentLoginGetIntegral) {
        this.currentLoginGetIntegral = currentLoginGetIntegral;
    }
}
