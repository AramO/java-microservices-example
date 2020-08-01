package com.aram.organization.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 *
 * @author aram
 */
@Component
@Configuration
public class ServiceConfig {

    @Value("${signing.key}")
    private final String jwtSigningKey = "";

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }
}
