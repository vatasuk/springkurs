package com.example.springkurs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication()
public class SpringkursApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringkursApplication.class, args);
    }

}
