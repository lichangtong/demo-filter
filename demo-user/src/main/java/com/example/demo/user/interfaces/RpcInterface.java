package com.example.demo.user.interfaces;

import com.example.config.para.RequestParas;
import com.example.config.result.ApiResult;
import com.example.demo.user.interfaces.impl.RpcInterfaceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: lichangtong
 * @Date: 2018/10/25 0025 11:30
 * @Description:
 */
@FeignClient(value = "search", path = "/brand", fallback = RpcInterfaceImpl.class)
public interface RpcInterface {
    /**
     * 信息查询
     *
     * @param requestParas
     * @return
     */
    @RequestMapping(value = "/rpcBrandInfo",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResult brandInfo(@RequestBody RequestParas requestParas);

    /**
     * 详情查询
     *
     * @param requestParas
     * @return
     */
    @RequestMapping(value = "/rpcBrandDetail",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResult brandDetail(@RequestBody RequestParas requestParas);
}
