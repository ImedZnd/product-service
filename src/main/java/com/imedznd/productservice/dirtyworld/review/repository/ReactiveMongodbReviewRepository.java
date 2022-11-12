package com.imedznd.productservice.dirtyworld.review.repository;

import com.imedznd.productservice.dirtyworld.review.dao.ReviewDAO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ReactiveMongodbReviewRepository extends ReactiveMongoRepository<ReviewDAO, String> {

    Flux<ReviewDAO> findByUserId(String userId);
}
