package com.example.config.util;

import com.example.config.result.ApiResult;

/**
 * @Auther: lichangtong
 * @Date: 2018/10/29 0029 10:36
 * @Description: 静态方法统一服务异常处理
 */
public class ApiResultHelper {
    public static ApiResult InitSuccess() {
        return new ApiResult.Builder().success().build();
    }

    public static ApiResult Initfailure(String message) {
        return new ApiResult.Builder().failure(message).build();
    }
}
