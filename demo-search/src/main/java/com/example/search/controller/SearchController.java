package com.example.search.controller;

import com.example.config.para.RequestParas;
import com.example.config.result.ApiResult;
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
    @RequestMapping("/search")
    public ApiResult search() {
        System.out.println("Search  say Hei 3333");
        ApiResult apiResult = new ApiResult();
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
}
