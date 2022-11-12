package com.imedznd.productservice.dirtyworld.review.config;

import com.imedznd.productservice.cleanworld.review.repository.ReviewRepository;
import com.imedznd.productservice.dirtyworld.review.repository.MongoReviewRepository;
import com.imedznd.productservice.dirtyworld.review.repository.ReactiveMongodbReviewRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReviewConfig {

    @Bean
    public ReviewRepository reactiveReviewRepository(
            final ReactiveMongodbReviewRepository reactiveMongodbReviewRepository
    ) {
        return MongoReviewRepository.getInstance(reactiveMongodbReviewRepository);
    }

}
