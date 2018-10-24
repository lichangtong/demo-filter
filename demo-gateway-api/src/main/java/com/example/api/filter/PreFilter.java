package com.example.api.filter;

import com.alibaba.fastjson.JSON;
import com.example.config.exception.ApiException;
import com.example.config.para.RequestParas;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@Component
public class PreFilter extends ZuulFilter {
    @Value("#{'${api.String.prefix}'.split(',')}")
    private String[] prefixs;

    @Override
    public String filterType() {
//        filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型
//        pre：路由之前
//        routing：路由之时
//        post： 路由之后
//        error：发送错误调用
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //这里可以写逻辑判断，是否要过滤，本文true,永远过滤
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String requestPath = request.getRequestURI();
        boolean bo = false;
        for (String prefix : prefixs) {
            if (StringUtils.startsWith( requestPath.toLowerCase(),prefix)) {
                bo = true;
            }
        }
        if (!bo) {
            throw new ApiException(500, "系统不支持的请求方式");
        }
        InputStream in;
        String reqBody;
        try {
            in = request.getInputStream();
            reqBody = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
            if (StringUtils.isBlank(reqBody) || reqBody.indexOf("{") < 0 || reqBody.length() <= 2) {
                throw new ApiException(100, "入参格式不正确");
            } else {
                //记录入参，并检验参数格式是否正确
                RequestParas requestParas = JSON.parseObject(reqBody, RequestParas.class);
                request.setAttribute("requestParas", requestParas);
                System.out.println(requestPath);
            }
        } catch (IOException e) {
            throw new ApiException(500, "系统内部错误");
        }

        return null;
    }
}