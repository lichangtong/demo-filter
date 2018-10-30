package com.example.search.interfaces;

import com.example.config.para.RequestParas;
import com.example.config.result.ApiResult;
import com.example.search.interfaces.impl.RpcInterfaceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: lichangtong
 * @Date: 2018/10/25 0025 10:05
 * @Description:
 */
@FeignClient(value = "user", path = "/user", fallback = RpcInterfaceImpl.class)
public interface RpcInterface {
    @RequestMapping(value = "/userInfo",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResult userInfo(@RequestBody RequestParas requestParas);
}
