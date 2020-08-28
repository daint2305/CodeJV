/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj.media.config;

import com.pj.media.util.Utils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Thanh_Long
 */
public class SessionManager implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {

        /*
        String xHeader = request.getHeader("X-Auth-Token");
        String requestUrl = request.getRequestURI();
   
        boolean permission = getPermission(xHeader);   
        if (permission) {
            return true;
        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
            // Above code will send a 401 with no response body.
            // If you need a 401 view, do a redirect instead of
            // returning false.
            // response.sendRedirect("/401"); // assuming you have a handler mapping for 401

        }
         */
        
        String contextPath = request.getContextPath();
        String requestUrl = request.getRequestURI();
        System.out.println("requestUrl : "+requestUrl);
        
        
//        if(requestUrl.endsWith("/login") || requestUrl.endsWith("/logout")  ){
//             return true;
//        }
        
        if(Utils.getUsersInSession(request)==null){
            //response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.sendRedirect(contextPath+"/login");
            return false;
        }
        
        
        return true;
    }

    private boolean getPermission(String xHeader) {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}
