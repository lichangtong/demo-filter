package com.example.config.constant;

/**
 * @Auther: lichangtong
 * @Date: 2018/10/29 0029 11:20
 * @Description:
 */
public enum ResultCode {
    /**
     * 请求成功
     */
    SUCCESS(200, "SUCCESS", ""),
    /**
     * 请求异常
     */
    EXCEPTION(300, "EXCEPTION", "服务异常稍后重试..."),
    /**
     * 请求成失败
     */
    FAILURE(400, "FAILURE", "服务请求失败...");

    ResultCode(int code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    private int code;
    private String status;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
