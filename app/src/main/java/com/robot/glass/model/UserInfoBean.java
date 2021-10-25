package com.robot.glass.model;

/**
 * @program: fcj
 * @description: ${description}
 * @author: zk
 * @create: 2019-08-15 11:57
 **/
public class UserInfoBean {
    private int id;
    private String uid;
    private String mobile;
    private String password;
    private String salt;
    private int sex;
    private String birthYear;
    private String birthMonth;
    private String birthDay;
    private int status;
    private int memberLevel;//会员等级  0：普通用户 1：黄金会员 2：铂金会员
    private int scores;
    private String nickname;
    private String avatar;
    private String lastIp;
    private String createTime;
    private String updateTime;
    private int isDelete;
    private String token;

    private int totalIntegral;
    private float totalGetReturnCash;
    private String expireTime;

    private float alreadyWithdrawCash;
    private float waitAuditReturnCash;
    private float availableWithdrawCash;


    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public float getAvailableWithdrawCash() {
        return availableWithdrawCash;
    }

    public void setAvailableWithdrawCash(float availableWithdrawCash) {
        this.availableWithdrawCash = availableWithdrawCash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(int memberLevel) {
        this.memberLevel = memberLevel;
    }

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setTotalIntegral(int totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    public float getTotalIntegral() {
        return totalIntegral;
    }

    public float getTotalGetReturnCash() {
        return totalGetReturnCash;
    }

    public void setTotalGetReturnCash(float totalGetReturnCash) {
        this.totalGetReturnCash = totalGetReturnCash;
    }

    public float getAlreadyWithdrawCash() {
        return alreadyWithdrawCash;
    }

    public void setAlreadyWithdrawCash(float alreadyWithdrawCash) {
        this.alreadyWithdrawCash = alreadyWithdrawCash;
    }

    public float getWaitAuditReturnCash() {
        return waitAuditReturnCash;
    }

    public void setWaitAuditReturnCash(float waitAuditReturnCash) {
        this.waitAuditReturnCash = waitAuditReturnCash;
    }
}
