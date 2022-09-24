package com.blog.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {

    private static final String AUTHORIZATION_HEADER= "Authorization";

    private ApiKey apiKeys(){
        return new ApiKey("JWT",AUTHORIZATION_HEADER,"header");
    }

    private List<SecurityContext> securityContext(){
        return Collections.singletonList(SecurityContext.builder().securityReferences(securityReference()).build());
    }

    private List<SecurityReference> securityReference(){
         AuthorizationScope authorizationScope = new AuthorizationScope("global","accessEverything");
        return Collections.singletonList(new SecurityReference("JWT", new AuthorizationScope[]{authorizationScope}));
    }

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .securityContexts(securityContext())
                .securitySchemes(Collections.singletonList(apiKeys()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo getInfo() {
        return new ApiInfo("Blogging API", "Developed by Mohd Shoeb","1.0","Terms of Service",new Contact("Shoeb","https://www.linkedin.com/in/mohd-shoeb/","wwe2k15flop@gmail.com"),"License of APIs","API License URL", Collections.emptyList());
    }
}
