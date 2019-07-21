package com.studentsco.store.dao;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@SpringBootConfiguration
@ComponentScan(basePackages = "com.studentsco.store")
@EnableJpaRepositories(basePackages = "com.studentsco.store.repositories")
public class Configuration {
    
    
}
