package com.robot.baselibs.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SplashBannerBean implements Serializable {
    private List<BannerBizBean> windowActivityVOList = new ArrayList<>();
    private List<BannerBizBean> splashActivityVOList = new ArrayList<>();
    private InviteBannerBean invitationWindowList;


    public List<BannerBizBean> getWindowActivityVOList() {
        return windowActivityVOList;
    }

    public void setWindowActivityVOList(List<BannerBizBean> windowActivityVOList) {
        this.windowActivityVOList = windowActivityVOList;
    }

    public List<BannerBizBean> getSplashActivityVOList() {
        return splashActivityVOList;
    }

    public void setSplashActivityVOList(List<BannerBizBean> splashActivityVOList) {
        this.splashActivityVOList = splashActivityVOList;
    }

    public InviteBannerBean getInvitationWindowList() {
        return invitationWindowList;
    }

    public void setInvitationWindowList(InviteBannerBean invitationWindowList) {
        this.invitationWindowList = invitationWindowList;
    }
}
