package com.personalprojects.gestorb2b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GestorB2BApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestorB2BApplication.class, args);
    }

}
