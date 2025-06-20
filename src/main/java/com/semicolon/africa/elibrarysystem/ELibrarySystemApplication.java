package com.semicolon.africa.elibrarysystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
public class ELibrarySystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ELibrarySystemApplication.class, args);
    }

}
