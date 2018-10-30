package com.example.config.result;

import com.example.config.constant.ResultCode;

import java.io.Serializable;

/**
 * API 结果统一返回类
 *
 * @param
 * @return
 */
public class ApiResult<T> implements Serializable {

    private int code;
    private String status;
    private String message;

    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ApiResult() {

    }

    public ApiResult(Builder<T> builder) {
        this.code = builder.code;
        this.message = builder.message;
        this.data = builder.data;
    }

    public static class Builder<T> {
        private int code;
        private String status;
        private String message;
        private T data;

        public ApiResult build() {
            return new ApiResult(this);
        }

        public Builder success() {
            this.code = ResultCode.SUCCESS.getCode();
            this.message = ResultCode.SUCCESS.getMessage();
            this.status = ResultCode.SUCCESS.getStatus();
            return this;
        }

        public Builder failure(T data) {
            this.code = ResultCode.FAILURE.getCode();
            this.message = ResultCode.FAILURE.getMessage();
            this.status = ResultCode.FAILURE.getStatus();
            this.data = data;
            return this;
        }

        public Builder success(String msg, T data) {
            this.code = ResultCode.SUCCESS.getCode();
            this.status = ResultCode.SUCCESS.getStatus();
            this.message = msg;
            this.data = data;
            return this;
        }

        public Builder failure() {
            this.code = ResultCode.FAILURE.getCode();
            this.message = ResultCode.FAILURE.getMessage();
            this.status = ResultCode.FAILURE.getStatus();
            return this;
        }

        public Builder failure(String msg) {
            this.code = ResultCode.FAILURE.getCode();
            this.message = msg;
            this.status = ResultCode.FAILURE.getStatus();
            return this;
        }

        public Builder failure(int code, String status, String msg) {
            this.code = code;
            this.status = status;
            this.message = msg;
            return this;
        }

        public Builder failure(int code, String status, String msg, T data) {
            this.code = code;
            this.status = status;
            this.message = msg;
            this.data = data;
            return this;
        }

        public Builder success(T data) {
            this.code = ResultCode.SUCCESS.getCode();
            this.message = ResultCode.SUCCESS.getMessage();
            this.status = ResultCode.SUCCESS.getStatus();
            this.data = data;
            return this;
        }

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
