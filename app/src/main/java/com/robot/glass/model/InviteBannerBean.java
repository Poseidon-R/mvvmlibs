package com.robot.glass.model;

/**
 * @program: fcj
 * @description:
 * @author: zk
 * @create: 2019-09-09 17:52
 **/
public class InviteBannerBean {
    /**
     * {
     * "cover": "string",
     * "id": 0,
     * "platinumCover": "string",
     * "rateType": 0,
     * "showNum": 0,
     * "userType": 0
     * }
     */
    private String cover;
    private int id;
    private String platinumCover;
    private int rateType;
    private int showNum;
    private int userType;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlatinumCover() {
        return platinumCover;
    }

    public void setPlatinumCover(String platinumCover) {
        this.platinumCover = platinumCover;
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

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
