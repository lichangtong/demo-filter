package com.example.search.controller;

import com.example.config.para.RequestParas;
import com.example.config.result.ApiResult;
import com.example.search.interfaces.RpcInterface;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Administrator on 2018/10/15/015.
 */
@RestController
@RequestMapping("/brand")
public class SearchController {
    @Autowired
    RpcInterface rpcInterface;

    @RequestMapping("/search")
    public ApiResult search(@RequestBody RequestParas requestParas) {
        System.out.println("Search  say Hei 3333");
        ApiResult apiResult = new ApiResult();
        return apiResult;
    }
    public ApiResult hiError() {
        ApiResult apiResult = new ApiResult();

        apiResult.setCode(4000);
        apiResult.setMessage("hiError");
        return apiResult;
    }
    @RequestMapping("/hai")
    public ApiResult helloTest(@RequestBody RequestParas requestParas) throws Exception {

        Map<String, String> map = requestParas.getMap();

        System.out.println(map);
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(200);
        apiResult.setMessage("SUCCESS");
        apiResult.setData(map);
        return apiResult;
    }

    @RequestMapping("/rpcUserInfo")
    public ApiResult userInfo(@RequestBody RequestParas requestParas) {
        return rpcInterface.userInfo(requestParas);
    }

    @RequestMapping("/rpcBrandInfo")
    public ApiResult brandInfo(@RequestBody RequestParas requestParas) {
        Map<String, String> map = requestParas.getMap();
        System.out.println(map);
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(400);
        apiResult.setMessage("SUCCESS--test");
        apiResult.setData(map);
        return apiResult;
    }
}
