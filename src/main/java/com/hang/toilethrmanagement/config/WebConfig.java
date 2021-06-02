package com.hang.toilethrmanagement.config;

import com.hang.toilethrmanagement.interceptor.MyselfInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private MyselfInterceptor myselfInterceptor;

    @Autowired
    public void setMyselfInterceptor(MyselfInterceptor myselfInterceptor) {
        this.myselfInterceptor = myselfInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(myselfInterceptor).addPathPatterns("/**");
    }
}
