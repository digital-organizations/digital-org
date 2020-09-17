package com.engg.digitalorg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * The type Swagger config.
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    /**
     * Api docket.
     *
     * @return the docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.engg.digitalorg"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * Api info api info.
     *
     * @return the api info
     */
    @Bean
    ApiInfo apiInfo()
    {
        return new ApiInfo("A digital organization services",
                "These services demonstrates some services "
                        + "related to card and group."
                        + "It serve purpose of managing the urls for any organization",
                "1.0.0",
                "https://digital-org.herokuapp.com/",
                new Contact("Abhay Shukla",
                        "https://github.com/abhayshukla04",
                        "abhay.shukla04@gmail.com"),
                "The Apache Software License, Version 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0.txt",
                Collections.EMPTY_LIST
        );
    }
}
