package com.iretailer.controller.response;

/**
 * Created by zhongjing on 2017/05/11.
 */
public enum ErrorMessage {
    SYSTEM_ERROR("-10000","系统异常：{0}"),
    USERNAME_NOT_NULL("-10001","{0}不能为空"),
    CONNECTION_EXCEPTION("-10003","连接异常"),
    NOT_LOGIN("-10004","未登录"),
    NOT_EXIST("-10005","用户 {0} 不存在"),
    NOT_AUTHORITY("-10006","没有权限"),
    EXIST("-10007","{0}已存在"),
    STATUS_ERROR("-10008","规则处于待触发状态，请勿编辑"),
    IS_AVAILABLE_ERROR("-10009","该模板处于启用状态，请勿编辑"),
    TRIGGERTIME_ERROR("-10010","有效时间不能小于当前系统时间"),
    PARAM_ERROR("-10011","参数有误"),
    PW_ERROR("-10012","密码错误");
    private String code;
    private String msg;

    ErrorMessage(String code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
