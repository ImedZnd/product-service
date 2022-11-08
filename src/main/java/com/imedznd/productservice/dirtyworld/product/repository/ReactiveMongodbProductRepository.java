package com.imedznd.productservice.dirtyworld.product.repository;

import com.imedznd.productservice.cleanworld.product.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactiveMongodbProductRepository extends ReactiveMongoRepository<Product, String> {
}
