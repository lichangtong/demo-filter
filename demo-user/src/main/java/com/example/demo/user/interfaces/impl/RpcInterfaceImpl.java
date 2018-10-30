package com.example.demo.user.interfaces.impl;

import com.example.config.para.RequestParas;
import com.example.config.result.ApiResult;
import com.example.config.util.ApiResultHelper;
import com.example.demo.user.interfaces.RpcInterface;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Auther: lichangtong
 * @Date: 2018/10/26 0026 11:30
 * @Description: rpc 接口熔断实现
 */
@Component
public class RpcInterfaceImpl implements RpcInterface {
    @Override
    public ApiResult brandInfo(RequestParas requestParas) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("message", "服务不可用");
        return ApiResultHelper.Initfailure("服务不可用");
    }

    /**
     * 详情查询
     *
     * @param requestParas
     * @return
     */
    @Override
    public ApiResult brandDetail(RequestParas requestParas) {
        return null;
    }
}
