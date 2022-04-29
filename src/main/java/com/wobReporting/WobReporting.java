package com.wobReporting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class WobReporting {

    public static void main(String[] args) {
        SpringApplication.run(WobReporting.class, args);
    }
}
