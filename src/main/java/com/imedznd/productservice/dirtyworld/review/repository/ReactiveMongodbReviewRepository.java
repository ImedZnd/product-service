package com.imedznd.productservice.dirtyworld.review.repository;

import com.imedznd.productservice.dirtyworld.review.dao.ReviewDAO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactiveMongodbReviewRepository extends ReactiveMongoRepository<ReviewDAO, String> {
}
