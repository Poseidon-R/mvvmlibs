package com.robot.baselibs.model;

/**
 * 创建日期：2020/9/14  12:42
 * 类说明:
 *
 * @author：robot
 */
public class BaseResponse<T> {
    private int code;
    private String message;
    private T data;

    private String apiName;//本地维护数据

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", apiName='" + apiName + '\'' +
                '}';
    }
}
