package com.example.api.filter;

import com.alibaba.fastjson.JSON;
import com.example.config.exception.ApiException;
import com.example.config.para.RequestParas;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PreFilter extends ZuulFilter {
    @Value("#{'${api.String.prefix}'.split(',')}")
    private String[] prefixs;
    private Map<String, Object> params = Maps.newHashMap();

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
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String requestURI = request.getRequestURI();

        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String requestPath = request.getRequestURI();
        System.out.println(requestPath);
        boolean bo = false;
        for (String prefix : prefixs) {
            if (StringUtils.startsWith(requestPath.toLowerCase(), prefix)) {
                bo = true;
            }
        }
        if (!bo) {
            throw new ApiException(500, "系统不支持的请求路径");
        }
        InputStream in;
        String reqBody;
        try {
            if (ServletFileUpload.isMultipartContent(request)) {
                ServletFileUpload fileUpload = new ServletFileUpload();
                FileItemIterator iter = fileUpload.getItemIterator(request);
                RequestParas requestParas = new RequestParas();
                while (iter.hasNext()) {
                    FileItemStream item = iter.next();
                    RequestParas.setParams(item, requestParas);
                }
                //用于传递一下通用数据，如解密后的userId,orderId,等
//                Map<String, List<String>> requestQueryParams = ctx.getRequestQueryParams();
//                ArrayList arrayList = Lists.newArrayList();
//                arrayList.add("李畅通=test-1233456#$$#%$%^");
//                if (requestQueryParams==null) {
//                    requestQueryParams=new HashMap<>();
//                    requestQueryParams.put("abcde",arrayList);
//                }
//                ctx.setRequestQueryParams(requestQueryParams);
            } else {
                in = request.getInputStream();
                reqBody = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
                if (StringUtils.isBlank(reqBody) || reqBody.indexOf("{") < 0 || reqBody.length() <= 2) {
                    throw new ApiException(100, "入参格式不正确");
                } else {
                    /*
                    记录入参，并检验参数格式是否正确
                     */
                    RequestParas requestParas = JSON.parseObject(reqBody, RequestParas.class);
                    request.setAttribute("requestParas", requestParas);
                    System.out.println(requestPath);
                }
            }

        } catch (Exception e) {
            throw new ApiException(500, "系统内部错误");
        }

        return null;
    }


}