package com.example.api.filter;

import com.example.config.result.ApiResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * zuul 全局错误处理控制器
 *
 * @param
 * @return
 */
@RestController
public class ApiErrorController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public ApiResult error(HttpServletRequest request) {
        ApiResult apiResult = new ApiResult();
        String msaage = (String) request.getAttribute("errorDoTest");
        if (StringUtils.isNotBlank(msaage)) {
            System.out.println(request.getAttribute("errorDoTest"));
            apiResult.setMessage(msaage);
            apiResult.setCode(1001);
            apiResult.setData(new HashMap<>());
        } else {
            apiResult.setMessage("系统内部异常");
            apiResult.setCode(500);
            apiResult.setData(new HashMap<>());
        }
        return apiResult;
    }
}
