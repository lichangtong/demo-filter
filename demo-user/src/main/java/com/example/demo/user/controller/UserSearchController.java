package com.example.demo.user.controller;

import com.example.config.para.RequestParas;
import com.example.config.result.ApiResult;
import com.example.demo.user.interfaces.RpcInterface;
import com.example.demo.user.interfaces.TurbineInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: lichangtong
 * @Date: 2018/10/25 0025 10:40
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserSearchController {

    @Autowired
    private RpcInterface rpcInterface;
    @Autowired
    private TurbineInterface turbineInterface;

    @RequestMapping("/userInfo")
    public ApiResult queryUserInfo(@RequestBody RequestParas requestParas) {
        return turbineInterface.turbineInfo(requestParas);
    }

    @RequestMapping("/brand")
    public ApiResult brandInfo(@RequestBody RequestParas requestParas) {
        return rpcInterface.brandInfo(requestParas);
    }
}
