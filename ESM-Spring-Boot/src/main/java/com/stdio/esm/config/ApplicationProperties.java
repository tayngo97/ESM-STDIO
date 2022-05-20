package com.stdio.esm.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application",ignoreUnknownFields = false)
@Getter
@Setter
public class ApplicationProperties {
    private Security security;
    @Getter
    @Setter
    public static class Security{
        private String passwordSecret;
        private long durationAccessToken;
        private long durationRefreshToken;
    }
}
