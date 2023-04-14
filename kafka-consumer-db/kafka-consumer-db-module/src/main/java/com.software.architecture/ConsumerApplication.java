package com.software.architecture;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
// import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// @ComponentScan
// @EnableAutoConfiguration
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class);
    }
}

// example published incident event
// {"id": "id", "title": "new incident1", "description": "Incident 1 description", "address": "address", "phone" : 12345}