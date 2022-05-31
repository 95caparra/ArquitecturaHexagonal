package com.movii.hexagonal.commons.properties;


import com.movii.hexagonal.commons.helper.ConstantsHelper;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = ConstantsHelper.SPRING_CONFIG_PREFIX)
public class GlobalProperties {

    /**
     * Name of Component
     */
    private String name;

    /**
     * Version of component
     */
    private String version;

    /**
     * Port on component running
     */
    private Integer restPort;

    /**
     * context path of component
     */
    private String root;

}
