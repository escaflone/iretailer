package com.iretailer.Interceptor;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wubin on 2017/6/28.
 */
public class AllowOriginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String fromUrl = request.getHeader("Referer");

        if(!StringUtils.isEmpty(fromUrl)){
            String tempStr = fromUrl.substring(7, fromUrl.length());
            fromUrl = "http://" + tempStr.substring(0,tempStr.indexOf("/"));
        }else{
            fromUrl = "*";
        }
        response.setHeader("Access-Control-Allow-Origin", fromUrl);
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with,authorization");
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Methods","POST, GET, DELETE, PUT");
        return super.preHandle(request, response, handler);
    }
}
