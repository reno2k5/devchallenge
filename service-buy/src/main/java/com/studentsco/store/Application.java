package com.studentsco.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.studentsco.store")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
