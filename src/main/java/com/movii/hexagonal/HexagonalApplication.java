package com.movii.hexagonal;

import com.movii.hexagonal.commons.helper.ConstantsHelper;
import com.movii.hexagonal.commons.properties.GlobalProperties;
import com.movii.hexagonal.infraestructure.out.restricted.RestrictedProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@EnableConfigurationProperties({
        RestrictedProperties.class,
        GlobalProperties.class
})
@SpringBootApplication
public class HexagonalApplication implements ApplicationListener<ContextRefreshedEvent> {

    private final GlobalProperties globalProperties;

    public HexagonalApplication(GlobalProperties globalProperties) {
        this.globalProperties = globalProperties;
    }

    public static void main(String[] args) {
        SpringApplication.run(HexagonalApplication.class, args);
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info(ConstantsHelper.LONG_LINE);
        log.info(ConstantsHelper.LOG_START_PROJECT, globalProperties.getName());
        log.info(ConstantsHelper.LOG_PORT_OF_PROJECT, globalProperties.getRestPort());
        log.info(ConstantsHelper.LOG_PROJECT_VERSION, globalProperties.getVersion());
        log.info(ConstantsHelper.LOG_RUN_OK);
        log.info(ConstantsHelper.LONG_LINE);
    }
}
