package com.mujun.mng.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestInterceptor extends HandlerInterceptorAdapter {


    /**
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     * 此方法可对所有请求统一拦截处理
     * author:cj
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
          return super.preHandle(request, response, handler);
    }



}
