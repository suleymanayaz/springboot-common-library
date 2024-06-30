package com.dedicoder.common.library.interceptor;

import com.dedicoder.common.library.config.CorrelationIdHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CorrelationInterceptor implements HandlerInterceptor {


    public CorrelationInterceptor() {
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String correlationId = request.getHeader("x-correlation-id");
        if (correlationId == null || correlationId.isEmpty()) {
            correlationId = CorrelationIdHolder.getCorrelationId();
        }

        CorrelationIdHolder.setCorrelationId(correlationId);
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        CorrelationIdHolder.clearCorrelationId();
    }

}
