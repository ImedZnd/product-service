package com.imedznd.productservice.dirtyworld.product.repository;

import com.imedznd.productservice.cleanworld.product.model.Product;
import com.imedznd.productservice.cleanworld.product.repository.ProductRepository;
import com.imedznd.productservice.dirtyworld.product.dao.ProductDAO;
import io.vavr.control.Either;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class MongoProductRepository implements ProductRepository {

    private static MongoProductRepository instance = null;
    private final ReactiveMongodbProductRepository reactiveMongodbProductRepository;

    public MongoProductRepository(final ReactiveMongodbProductRepository reactiveMongodbProductRepository) {
        this.reactiveMongodbProductRepository = reactiveMongodbProductRepository;
    }

    public static synchronized MongoProductRepository getInstance(
            final ReactiveMongodbProductRepository reactiveMongodbProductRepository
    ) {
        if (Objects.isNull(instance))
            instance = new MongoProductRepository(reactiveMongodbProductRepository);
        return instance;
    }

    @Override
    public Flux<Product> findAll() {
        return reactiveMongodbProductRepository
                .findAll()
                .map(ProductDAO::toModel)
                .filter(Either::isRight)
                .map(Either::get);
    }

    @Override
    public Mono<Product> findById(String id) {
        return reactiveMongodbProductRepository
                .findById(id)
                .map(ProductDAO::toModel)
                .filter(Either::isRight)
                .map(Either::get);
    }

    @Override
    public Mono<Product> save(Product product) {
        return reactiveMongodbProductRepository
                .save(ProductDAO.toDAO(product))
                .map(ProductDAO::toModel)
                .filter(Either::isRight)
                .map(Either::get);
    }

    @Override
    public Mono<Product> delete(String id) {
        Mono<Product> product  =  findById(id);
        reactiveMongodbProductRepository.deleteById(id);
        return product;
    }

    @Override
    public Flux<Product> findByCategory(String category) {
        return reactiveMongodbProductRepository
                .findAllByCategory(category)
                .map(ProductDAO::toModel)
                .filter(Either::isRight)
                .map(Either::get);
    }
}
