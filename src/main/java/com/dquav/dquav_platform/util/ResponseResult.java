package com.dquav.dquav_platform.util;

/**
 * @author TrEx
 * @date 2021/5/8 - 15:13
 */
public class ResponseResult<T> {
    private Integer state;
    private String message;
    private T data;

    public ResponseResult() {
    }

    public ResponseResult(Integer state) {
        this.state = state;
    }

    public ResponseResult(Integer state, T data) {
        this.state = state;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "state=" + state +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
}
