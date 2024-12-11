package com.company.project.config;

import com.company.project.intercepter.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/html/**")
                .addResourceLocations("classpath:/static/**")
                .setCacheControl(CacheControl.maxAge(864000, TimeUnit.SECONDS).cachePublic());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())//添加拦截器
                .excludePathPatterns("/login","/register","/html/**")//对应的不拦截的请求
                .addPathPatterns("/**"); //拦截所有请求
    }


    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }
}
