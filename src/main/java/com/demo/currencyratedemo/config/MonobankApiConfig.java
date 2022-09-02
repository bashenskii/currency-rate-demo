package com.demo.currencyratedemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "monobank.api")
@Data
public class MonobankApiConfig {

    private String url;
}
