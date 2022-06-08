package com.example.alpha.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "giphy")
@Data
public class GiphyConfig {
    private String apiKey;
    private String url;
}
