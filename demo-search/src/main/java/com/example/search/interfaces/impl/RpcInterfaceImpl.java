package com.example.search.interfaces.impl;

import com.example.config.para.RequestParas;
import com.example.config.result.ApiResult;
import com.example.config.util.ApiResultHelper;
import com.example.search.interfaces.RpcInterface;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @Auther: lichangtong
 * @Date: 2018/10/29 0029 09:48
 * @Description: 熔断默认实现处理
 */
@Component
public class RpcInterfaceImpl implements RpcInterface {
    @Override
    public ApiResult userInfo(@RequestBody RequestParas requestParas) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("message","服务异常请稍后重试");
        return ApiResultHelper.Initfailure("服务异常请稍后重试");
    }

}
