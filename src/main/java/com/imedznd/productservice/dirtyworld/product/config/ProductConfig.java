package com.imedznd.productservice.dirtyworld.product.config;

import com.imedznd.productservice.cleanworld.product.repository.ProductRepository;
import com.imedznd.productservice.dirtyworld.product.repository.MongoProductRepository;
import com.imedznd.productservice.dirtyworld.product.repository.ReactiveMongodbProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    public ProductRepository reactiveProductRepository(
            ReactiveMongodbProductRepository reactiveMongodbProductRepository
    ){
        return MongoProductRepository.getInstance(reactiveMongodbProductRepository);
    }

}
