package com.engg.digitalorg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.engg.digitalorg.repository")
public class DigitalOrgApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalOrgApplication.class, args);
    }

}
