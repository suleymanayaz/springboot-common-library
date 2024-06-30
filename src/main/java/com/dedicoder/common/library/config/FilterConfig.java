package com.dedicoder.common.library.config;

import com.dedicoder.common.library.filter.HttpListenerFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<HttpListenerFilter> loggingFilter(){
        FilterRegistrationBean<HttpListenerFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new HttpListenerFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}