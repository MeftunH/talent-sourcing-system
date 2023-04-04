package com.tss.talentsourcingsystem.application.config;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI customOpenAPI(
            @Value("${application.TITLE}") String appTitle,
            @Value("${application.DESCRIPTION}") String appDescription,
            @Value("${application.VERSION}") String appVersion)

    {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title(appTitle)
                        .version(appVersion)
                        .description(appDescription)
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                );

    }
}
