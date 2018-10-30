package com.example.demo.controller;

import com.example.config.para.RequestParas;
import com.example.config.result.ApiResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: lichangtong
 * @Date: 2018/10/29 0029 15:52
 * @Description:
 */
@RestController
public class TurbineController {
    @RequestMapping("/turbine")
    public ApiResult turbineInfo(@RequestBody RequestParas requestParas) {
        System.out.println("Turbine info 。。。。。");

        ApiResult apiResult = new ApiResult();
        apiResult.setCode(200);
        apiResult.setMessage("message!!!!'");
        return apiResult;
    }
}
