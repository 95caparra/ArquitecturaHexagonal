package com.movii.hexagonal.infraestructure.out.restricted;

import com.movii.hexagonal.infraestructure.out.RestConnector;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@AllArgsConstructor
@Configuration
public class RestrictedRestConnector {
    private final RestrictedProperties restrictedProperties;


    /**
     * Bean to create rest connection to userMovii
     *
     * @return rest connector to userMovii
     */
    @Bean("restrictedConnector")
    public RestConnector restrictedConnector() {
        return new RestConnector(
                this.restrictedProperties.getUrl(),
                this.restrictedProperties.getConnectionTimeout(),
                this.restrictedProperties.getReadTimeout()
        );
    }
}
