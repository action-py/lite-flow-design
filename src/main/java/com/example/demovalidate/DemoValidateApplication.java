package com.example.demovalidate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.example.demovalidate"})
public class DemoValidateApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoValidateApplication.class, args);
    }

}
