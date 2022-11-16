package com.imedznd.productservice.cleanworld.review.repository;

import com.imedznd.productservice.cleanworld.review.model.Review;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReviewRepository {

    Flux<Review> findAll();

    Mono<Review> findById(String id);

    Mono<Review> save(Review review);

    Mono<Review> delete(String id);

    Flux<Review> findByUserId(String userId);

    sealed interface RepositoryReviewError{

        String message();

        record ReviewIOError(String message)  implements RepositoryReviewError{
            public ReviewIOError(){
                this("");
            }
        }
    }
}
