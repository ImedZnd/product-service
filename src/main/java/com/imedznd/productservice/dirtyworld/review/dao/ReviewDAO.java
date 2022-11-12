package com.imedznd.productservice.dirtyworld.review.dao;

import com.imedznd.productservice.cleanworld.review.model.Review;
import io.vavr.control.Either;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Collection;

@Document(value = "review")
public record ReviewDAO(
        @Id
        String id,
        String description,
        String title,
        int rating,
        String userId,
        Instant createdDate,
        Instant lastUpdatedDate
) {

    public static ReviewDAO toDAO(Review review) {
        return new ReviewDAO(
                review.getId(),
                review.getDescription(),
                review.getTitle(),
                review.getRating(),
                review.getUserId(),
                review.getCreatedDate(),
                review.getLastUpdatedDate()
        );
    }

    public static Either<Collection<? extends Review.ReviewError>, Review> toModel(ReviewDAO reviewDAO) {
        return Review.of(
                reviewDAO.id,
                reviewDAO.description,
                reviewDAO.title,
                reviewDAO.rating,
                reviewDAO.userId,
                reviewDAO.createdDate,
                reviewDAO.lastUpdatedDate
        );
    }

}
