package com.engg.digitalorg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableJpaRepositories(basePackages = "com.engg.digitalorg.repository")
public class DigitalOrgApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalOrgApplication.class, args);
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(100000);
        return multipartResolver;
    }

}
