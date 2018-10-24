package com.example.api.filter;

import com.alibaba.fastjson.JSON;
import com.example.config.result.ApiResult;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/10/17/017.
 */
@Component
public class PostFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        if (ctx.getResponseStatusCode() != 200) {
            ApiResult apiResult = new ApiResult();
            apiResult.setCode(ctx.getResponseStatusCode());
            apiResult.setMessage(ctx.getRequest().getRequestURI());
            ctx.setResponseBody(JSON.toJSONString(apiResult));
        }
        System.out.println("FilterConstants.POST_TYPE;  URL:" + ctx.getRequest().getRequestURI() + " 请求结束处理日志打印");
        return null;
    }


}
