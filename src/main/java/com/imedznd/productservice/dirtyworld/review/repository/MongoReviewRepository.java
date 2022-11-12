package com.imedznd.productservice.dirtyworld.review.repository;

import com.imedznd.productservice.cleanworld.review.model.Review;
import com.imedznd.productservice.cleanworld.review.repository.ReviewRepository;
import com.imedznd.productservice.dirtyworld.review.dao.ReviewDAO;
import io.vavr.control.Either;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class MongoReviewRepository implements ReviewRepository {

    private static MongoReviewRepository instance = null;

    private final ReactiveMongodbReviewRepository reactiveMongodbReviewRepository;

    public MongoReviewRepository(ReactiveMongodbReviewRepository reactiveMongodbReviewRepository) {
        this.reactiveMongodbReviewRepository = reactiveMongodbReviewRepository;
    }

    public static synchronized MongoReviewRepository getInstance(
            final ReactiveMongodbReviewRepository reactiveMongodbReviewRepository
    ){
        if (Objects.isNull(instance))
            instance = new  MongoReviewRepository(reactiveMongodbReviewRepository);
        return instance;
    }

    @Override
    public Flux<Review> findAll() {
        return reactiveMongodbReviewRepository
                .findAll()
                .map(ReviewDAO::toModel)
                .filter(Either::isRight)
                .map(Either::get);
    }

    @Override
    public Mono<Review> findById(String id) {
        return reactiveMongodbReviewRepository
                .findById(id)
                .map(ReviewDAO::toModel)
                .filter(Either::isRight)
                .map(Either::get);
    }

    @Override
    public Mono<Review> save(Review review) {
        return reactiveMongodbReviewRepository
                .save(ReviewDAO.toDAO(review))
                .map(ReviewDAO::toModel)
                .filter(Either::isRight)
                .map(Either::get);
    }

    @Override
    public Mono<Review> delete(String id) {
        Mono<Review> review = findById(id);
        reactiveMongodbReviewRepository.deleteById(id);
        return review;
    }

    @Override
    public Flux<Review> findByUserId(String userId) {
        return reactiveMongodbReviewRepository
                .findByUserId(userId)
                .map(ReviewDAO::toModel)
                .filter(Either::isRight)
                .map(Either::get);
    }
}
