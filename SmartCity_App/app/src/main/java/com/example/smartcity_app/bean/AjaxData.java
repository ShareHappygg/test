package com.example.smartcity_app.bean;

public class AjaxData <T> {

    private boolean verifySuccess;

    private  T userInfo;

    public boolean isVerifySuccess() {
        return verifySuccess;
    }

    public void setVerifySuccess(boolean verifySuccess) {
        this.verifySuccess = verifySuccess;
    }

    public T getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(T userInfo) {
        this.userInfo = userInfo;
    }
}
