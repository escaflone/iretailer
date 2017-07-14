package com.iretailer.controller.response;

import java.io.Serializable;

public class RestResponse<T> implements Serializable {
    public static final String MSG_SUCCESS = "success";
    private static final long serialVersionUID = -9169992654831884236L;

    private String code = "0";

    private String msg = MSG_SUCCESS;

    private T data;


    public RestResponse() {

    }

    public RestResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RestResponse(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
