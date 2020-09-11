package com.mujun.mng.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.mujun.mng.commons.config.SrConstantMDA;
import com.mujun.mng.controller.CountryLandDataController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestfulRequestFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(CountryLandDataController.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    // 跨域请求，添加header 信息
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS,PUT");
        response.setHeader("Access-Control-Max-Age", "0");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
        response.setHeader("Allow","GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");

        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(200);
            return;
        }

        String url = request.getRequestURL().toString();
        String urlParams = request.getQueryString();
        String contnetType = request.getContentType();

        logger.debug("url:" + url);
        logger.debug("urlParams:" + urlParams);
        logger.debug("contnetType:" + contnetType);

        // 登录，验证码获取  不要求身份验证
//        if(ifNotNeedCheck(url)){
//            chain.doFilter(req, res);
//            return;
//        }
//        else{
//            int resultCd = checkAccess(request, response);
//            String result = "";
//            switch(resultCd){
//                case 0 : chain.doFilter(req, res);return;
//                case 1 : result = ResponseUtils.failure(ApiErrorList.AuthError);break;
//                case 2 : result = ResponseUtils.failure(ApiErrorList.InvalidTokenError);break;
//                case 3 : result = ResponseUtils.failure(ApiErrorList.TokenExpError);break;
//                case 4 : result = ResponseUtils.failure(ApiErrorList.OtherError);break;
//            }
//            response.getOutputStream().write(result.getBytes("UTF-8"));
//            response.setContentType("text/json; charset=UTF-8");
//        }
    }

    private boolean ifNotNeedCheck(String url){
        String[] acceptUrls = SrConstantMDA.ACCEPT_URI.split(",");
        if (acceptUrls.length==0) return true;
        for(int i =0;i<acceptUrls.length;i++){
            if(url.indexOf(acceptUrls[i]) > -1){
                return true;
            }
        }
        return false;
    }

    /** 鉴权
     * @param request ..
     * @param response ..
     * @return ..
     * @throws ServletException
     */
    private int checkAccess(HttpServletRequest request,HttpServletResponse response) throws ServletException{

        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null /*|| !authHeader.startsWith("Bearer")*/) {
            // Missing or invalid Authorization header.
            return 1;
        }

        final String token = authHeader/*.substring(6)*/; // The part after "Bearer "

        try {
            JSONObject staffInfoJo = null;
            request.setAttribute("userId", staffInfoJo.getString("staffId"));
            request.setAttribute("regionId", staffInfoJo.getString("regionId"));
            request.setAttribute("systemUserId", staffInfoJo.containsKey("systemUserId")?staffInfoJo.getString("systemUserId"):"");//增加系统工号id
            request.setAttribute("staffCode", staffInfoJo.getString("staffCode"));//增加系统工号staffCode
            request.setAttribute("Authorization", authHeader);
            request.setAttribute("isSuperManager", staffInfoJo.getBoolean("isSuperManager"));
        }catch (final Exception e){
            // other
            return 4;
        }

        // success
        return 0;
    }


    @Override
    public void destroy() {

    }
}
