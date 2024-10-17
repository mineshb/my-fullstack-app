package com.mineshb.handson.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;

@Configuration
@RequiredArgsConstructor
public class PaginationConfig {

    private final PaginationProperties properties;

    @Bean
    public PageableHandlerMethodArgumentResolver pageableResolver() {
        return new PageableHandlerMethodArgumentResolver();
    }

    public int getDefaultPageSize() {
        return properties.getDefaultPageSize();
    }
}
