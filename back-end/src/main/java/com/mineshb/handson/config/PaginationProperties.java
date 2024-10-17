package com.mineshb.handson.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component("paginationProperties")
@Getter
@Setter
@Primary
public class PaginationProperties {

    @Value("${spring.data.web.pageable.default-page-size}")
    private int defaultPageSize;

    @Value("${spring.data.web.pageable.max-page-size}")
    private int maxSize;
}