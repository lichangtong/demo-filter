package com.example.demo.user.interfaces;

import com.example.config.para.RequestParas;
import com.example.config.result.ApiResult;
import com.example.demo.user.interfaces.impl.RpcInterfaceImpl;
import com.example.demo.user.interfaces.impl.RpcTurbineInterfaceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: lichangtong
 * @Date: 2018/10/29 0029 17:02
 * @Description: 测试熔断
 */
@FeignClient(value = "turbine", fallback = RpcTurbineInterfaceImpl.class)
public interface TurbineInterface {
    @RequestMapping(value = "/turbine",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResult turbineInfo(@RequestBody RequestParas requestParas);
}
