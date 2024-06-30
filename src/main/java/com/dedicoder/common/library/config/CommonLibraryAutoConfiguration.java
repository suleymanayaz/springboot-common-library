package com.dedicoder.common.library.config;

import com.dedicoder.common.library.exception.GeneralExceptionHandler;
import com.dedicoder.common.library.interceptor.HttpRequestInterceptor;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({BasePathConfig.class, RestTemplateConfig.class, WebClientConfig.class, WebConfig.class, GeneralExceptionHandler.class,FilterConfig.class, HttpRequestInterceptor.class})
public class CommonLibraryAutoConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(CommonLibraryAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {}

}
