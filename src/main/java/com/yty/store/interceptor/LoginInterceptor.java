package com.yty.store.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Yang
 * @create 2023-05-30 20:18
 * @Description: 拦截器对象 防止未登录用户访问权限页面
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     *  检查全局session 中是否有uid 有放行 否则 拦截重定向登录页面
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器（url + controller: 映射）
     * @return true 放行 false 拦截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object uid = request.getSession().getAttribute("uid");
        // 为空 拦截
        if(uid == null){
            response.sendRedirect("/web/login.html");
            return false;
        }
        return true;
    }
}
