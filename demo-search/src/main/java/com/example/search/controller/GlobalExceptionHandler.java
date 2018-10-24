package com.example.search.controller;

import com.example.config.result.ApiResult;
import com.example.search.global.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/10/18/018.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ApiResult errorHanler(MyException e) {

        ApiResult apiResult = new ApiResult();
        apiResult.setCode(e.getErrorCode());
        apiResult.setMessage(e.getMessage());

        return apiResult;
    }
}
