package com.example.alpha.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "exchange")
@Data
public class ExchangeConfig {
    private String apiKey;
    private String url;
    private String currency;
}
