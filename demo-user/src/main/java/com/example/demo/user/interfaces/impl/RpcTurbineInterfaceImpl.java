package com.example.demo.user.interfaces.impl;

import com.example.config.para.RequestParas;
import com.example.config.result.ApiResult;
import com.example.config.util.ApiResultHelper;
import com.example.demo.user.interfaces.TurbineInterface;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Auther: lichangtong
 * @Date: 2018/10/29 0029 17:03
 * @Description:
 */
@Component
public class RpcTurbineInterfaceImpl implements TurbineInterface {
    @Override
    public ApiResult turbineInfo(RequestParas requestParas) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("message", "服务不可用--turbine");
        return ApiResultHelper.Initfailure("服务不可用");
    }
}
