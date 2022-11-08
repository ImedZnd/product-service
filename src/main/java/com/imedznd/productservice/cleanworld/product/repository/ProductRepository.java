package com.imedznd.productservice.cleanworld.product.repository;

import com.imedznd.productservice.cleanworld.product.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository {

    Flux<Product> findAll();

    Mono<Product> findById(String id);

    Mono<Product> save(Product product);

    Mono<Product> delete(String id);

    Flux<Product> findByUserId(String userId);

    Flux<Product> findByCategory(String category);

}
