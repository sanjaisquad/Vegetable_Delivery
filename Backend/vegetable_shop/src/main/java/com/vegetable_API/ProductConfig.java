package com.vegetable_API;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {
    @Bean
    CommandLineRunner CommandLineRunner(ProductRepository repository) {
        return args -> {
            Product product1 = new Product("Apple",23,"fruit","Yes");

            repository.save(product1);



        };
    }
}

