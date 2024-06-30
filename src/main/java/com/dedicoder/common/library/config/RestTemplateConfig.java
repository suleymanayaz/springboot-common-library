package com.dedicoder.common.library.config;

import com.dedicoder.common.library.interceptor.HttpRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(HttpRequestInterceptor httpRequestInterceptor) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(httpRequestInterceptor);
        return restTemplate;
    }
}