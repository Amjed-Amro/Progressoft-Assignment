package com.bloomberg.fxdeals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan
@EnableJpaRepositories(basePackages = "com.bloomberg.fxdeals.repository")
public class FxDealsApplication {
    public static void main(String[] args) {
        SpringApplication.run(FxDealsApplication.class, args);
    }

}
