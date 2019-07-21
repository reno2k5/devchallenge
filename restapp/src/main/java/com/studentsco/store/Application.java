package com.studentsco.store;

import com.studentsco.store.dao.security.Role;
import com.studentsco.store.dao.security.User;
import com.studentsco.store.repositories.ProductJPARepository;
import com.studentsco.store.repositories.RoleJPARepository;
import com.studentsco.store.repositories.UsersJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.studentsco.store")
public class Application {

    @Autowired
    private RoleJPARepository rolRepo;

    @Autowired
    private UsersJPARepository userRepo;

    Role adminRol;
    Role userRol;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner initDB(ProductJPARepository productRepo) {
        return args -> {
            createRoles();
            createUsers();
        };
    }

    private void createRoles() {

        userRol = rolRepo.findByRole("USER");
        adminRol = rolRepo.findByRole("ADMIN");

        if (userRol == null) {
            userRol = new Role();
            userRol.setRole("USER");
            rolRepo.save(userRol);
        }

        if (adminRol == null) {
            adminRol = new Role();
            adminRol.setRole("ADMIN");
            rolRepo.save(adminRol);
        }
    }

    private void createUsers() {
        User adminUser = userRepo.findByUsername("admin");
        User regularUser = userRepo.findByUsername("user");

        if (adminUser == null) {
            adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword("password");
            adminUser.setEnabled(Boolean.TRUE);
            adminUser.getRoles().add(adminRol);
            adminUser.getRoles().add(userRol);
            userRepo.save(adminUser);
        }

        if (regularUser == null) {
            regularUser = new User();
            regularUser.setUsername("user");
            regularUser.setPassword("password");
            regularUser.setEnabled(Boolean.TRUE);
            regularUser.getRoles().add(userRol);
            userRepo.save(regularUser);
        }

    }
}
