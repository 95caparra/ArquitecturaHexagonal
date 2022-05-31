package com.movii.hexagonal.infraestructure.out.restricted;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "adapters.restricted")
public class RestrictedProperties {
    private String url;
    private Integer connectionTimeout;
    private Integer readTimeout;
}
