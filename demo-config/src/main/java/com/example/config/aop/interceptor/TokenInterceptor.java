package com.example.config.aop.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;

/**
 * @Auther: lichangtong
 * @Date: 2018/11/22 0022 15:35
 * @Description:
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("自定义拦截器。。。。。");
        Map<String,String[]> paraMap = request.getParameterMap();

        Iterator iterator = paraMap.keySet().iterator();
        while(iterator.hasNext()){
            String key = iterator.next().toString();
            System.out.println(paraMap.get(key)[0].toString());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("-----------------------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("-------666666666666666666----------------------");
    }
}
