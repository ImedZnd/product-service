package com.imedznd.productservice.dirtyworld.product.repository;

import com.imedznd.productservice.dirtyworld.product.dao.ProductDAO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ReactiveMongodbProductRepository extends ReactiveMongoRepository<ProductDAO, String> {

    Flux<ProductDAO> findAllByCategory(String category);
}
