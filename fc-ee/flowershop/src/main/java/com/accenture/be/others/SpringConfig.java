package com.accenture.be.others;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;

@SpringBootApplication
@ComponentScan("com.accenture")
@EntityScan("com.accenture")
@EnableJpaRepositories("com.accenture")
@EnableAutoConfiguration
public class SpringConfig {

    public static void main(String[] args) {

        SpringApplication.run(SpringConfig.class, args);
    }

}
