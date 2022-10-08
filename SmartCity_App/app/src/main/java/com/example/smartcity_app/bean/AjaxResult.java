package com.example.smartcity_app.bean;

public class AjaxResult<T>  {

    private  Integer code;


    private    AjaxData<T> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public AjaxData<T> getData() {
        return data;
    }

    public void setData(AjaxData<T> data) {
        this.data = data;
    }
}
