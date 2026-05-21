package org.example.session05.config;

import org.example.session05.model.entity.Product;
import org.example.session05.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() > 0) {
                return;
            }

            List<Product> products = List.of(
                    Product.builder()
                            .productName("Sach Java")
                            .description("Hoc Spring Boot")
                            .price(30000)
                            .stock(10)
                            .build(),
                    Product.builder()
                            .productName("Ban phim")
                            .description("Ban phim co day")
                            .price(250000)
                            .stock(5)
                            .build(),
                    Product.builder()
                            .productName("Chuot")
                            .description("Chuot khong day")
                            .price(180000)
                            .stock(8)
                            .build()
            );

            productRepository.saveAll(products);
        };
    }
}
