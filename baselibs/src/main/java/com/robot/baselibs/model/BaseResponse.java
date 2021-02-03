package com.robot.baselibs.model;

/**
 * 创建日期：2020/9/14  12:42
 * 类说明:
 *
 * @author：robot
 */
public class BaseResponse<T> {
    private int status;
    private String msg;
    private T data;
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
