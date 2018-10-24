package com.example.config.exception;

/**
 * 自定义错误异常类
 *
 * @param
 * @return
 */
public class ApiException extends RuntimeException {
    private int errorCode;
    private String message;

    public ApiException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
