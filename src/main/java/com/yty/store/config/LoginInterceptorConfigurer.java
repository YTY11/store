package com.yty.store.config;

import com.yty.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

/**
 * @author Yang
 * @create 2023-05-30 20:34
 * @Description: 拦截器注册
 */
@Configuration // 项目启动时加载拦截器
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 创建自定义的拦截器对象
        LoginInterceptor loginInterceptor = new LoginInterceptor();

        // 配置白名单
        ArrayList<String> pathList = new ArrayList<>();
        pathList.add("/bootstrap3/**");
        pathList.add("/images/**");
        pathList.add("/js/**");
        pathList.add("/css/**");
        pathList.add("/web/login.html");
        pathList.add("/web/register.html");
        pathList.add("/web/index.html");
        pathList.add("/index.html");
        pathList.add("/api/login");
        pathList.add("/api/register");
        pathList.add("/api/getByParent");

        // addInterceptor拦截器loginInterceptor注册
        // addPathPatterns (/**)拦截所有路径
        // excludePathPatterns（list集合）什么除外
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(pathList);
    }
}
