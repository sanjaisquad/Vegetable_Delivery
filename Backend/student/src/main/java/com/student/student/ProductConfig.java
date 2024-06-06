package com.student.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class ProductConfig {
    @Bean
    CommandLineRunner CommandLineRunner(ProductRepository repository) {
        return args -> {
            Product product1 = new Product("sadfdsdfd",23,"fruit","Yes");
            repository.save(product1);

        };
    }
}

