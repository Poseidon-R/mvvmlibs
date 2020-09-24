package com.robot.baselibs.model;

import java.io.Serializable;

public class BannerBizBean implements Serializable {
    /**
     * id : 46
     * cover : public/images/2019/7/3/yvkknpiz.png
     * activityType : 2
     * isTimeLimit : null
     * startTime : 2019-07-17 00:00:00
     * endTime : 2019-07-31 00:00:00
     * skipType : 1
     * skipContent :
     * userType : 3
     * rateType : 1
     * showNum : 3
     * remainTime : 4
     * showStartTime : 09:54:21
     * showEndTime : 23:56:21
     */

    private int id;
    private String cover;
    private int activityType;
    private int isTimeLimit;
    private String startTime;
    private String endTime;
    private int skipType;
    private String skipContent;
    private int userType;
    private int rateType;
    private int showNum;
    private int remainTime;
    private String showStartTime;
    private String showEndTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getActivityType() {
        return activityType;
    }

    public void setActivityType(int activityType) {
        this.activityType = activityType;
    }

    public int getIsTimeLimit() {
        return isTimeLimit;
    }

    public void setIsTimeLimit(int isTimeLimit) {
        this.isTimeLimit = isTimeLimit;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getSkipType() {
        return skipType;
    }

    public void setSkipType(int skipType) {
        this.skipType = skipType;
    }

    public String getSkipContent() {
        return skipContent;
    }

    public void setSkipContent(String skipContent) {
        this.skipContent = skipContent;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getRateType() {
        return rateType;
    }

    public void setRateType(int rateType) {
        this.rateType = rateType;
    }

    public int getShowNum() {
        return showNum;
    }

    public void setShowNum(int showNum) {
        this.showNum = showNum;
    }

    public int getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(int remainTime) {
        this.remainTime = remainTime;
    }

    public String getShowStartTime() {
        return showStartTime;
    }

    public void setShowStartTime(String showStartTime) {
        this.showStartTime = showStartTime;
    }

    public String getShowEndTime() {
        return showEndTime;
    }

    public void setShowEndTime(String showEndTime) {
        this.showEndTime = showEndTime;
    }
}
