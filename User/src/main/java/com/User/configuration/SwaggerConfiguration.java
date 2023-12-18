package com.User.configuration;


import com.User.controller.AdminController;
import com.User.controller.ClientController;
import com.User.controller.ManagerController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage(ClientController.class.getPackage().getName()))
                .apis(RequestHandlerSelectors.basePackage(AdminController.class.getPackage().getName()))
                .apis(RequestHandlerSelectors.basePackage(ManagerController.class.getPackage().getName()))
                .build();
    }

}