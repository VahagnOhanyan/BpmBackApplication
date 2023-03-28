package ru.ohanyan.bpm.fw;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "ru.ohanyan.bpm")
@EnableJpaRepositories(basePackages = "ru.ohanyan.bpm.app.repo")
@EntityScan(basePackages = "ru.ohanyan.bpm.domain")
@EnableProcessApplication
public class BpmApplication {

    public static void main(String[] args) {
        SpringApplication.run(BpmApplication.class, args);
    }
}
