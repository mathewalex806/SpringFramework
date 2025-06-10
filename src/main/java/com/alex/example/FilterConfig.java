package com.alex.example;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig
{
    @Bean
    public FilterRegistrationBean<RequestBodyCheckingFilter> bodyFilter() {
        FilterRegistrationBean<RequestBodyCheckingFilter> reg = new FilterRegistrationBean<>();
        reg.setFilter(new RequestBodyCheckingFilter());
        reg.addUrlPatterns("/"); // Target specific endpoint
        reg.setOrder(1);
        return reg;
    }
}
