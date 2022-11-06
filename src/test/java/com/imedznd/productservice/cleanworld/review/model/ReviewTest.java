package com.imedznd.productservice.cleanworld.review.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

class ReviewTest {

    @Test
    @DisplayName("review should be initiated with initial fields")
    void review_should_be_initiated_with_initial_fields() {
        final var description = "description";
        final var title = "title";
        final var rating = 5;
        final var userId = "userId";
        final var localDateTime = LocalDateTime.parse("2019-10-25T12:15:30");
        final var timeInSeconds = localDateTime.toEpochSecond(ZoneOffset.UTC);
        final var createdDate = Instant.ofEpochSecond(timeInSeconds);
        final var lastUpdatedDate = Instant.ofEpochSecond(timeInSeconds);
        final var result =
                Review.of(
                        description,
                        title,
                        rating,
                        userId,
                        createdDate,
                        lastUpdatedDate
                ).get();
        assertAll(
                () -> assertEquals(description, result.getDescription()),
                () -> assertEquals(title, result.getTitle()),
                () -> assertEquals(rating, result.getRating()),
                () -> assertEquals(userId, result.getUserId()),
                () -> assertEquals(createdDate, result.getCreatedDate()),
                () -> assertEquals(lastUpdatedDate, result.getLastUpdatedDate())
        );
    }
}