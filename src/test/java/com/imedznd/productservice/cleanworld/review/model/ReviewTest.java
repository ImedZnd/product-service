package com.imedznd.productservice.cleanworld.review.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

class ReviewTest {

    @Test
    @DisplayName("should be initiated with initial fields")
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
                Review.of(null,
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

    @Test
    @DisplayName("description must not be empty")
    void review_description_must_not_be_empty() {
        final var description = "";
        final var title = "title";
        final var rating = 5;
        final var userId = "userId";
        final var localDateTime = LocalDateTime.parse("2019-10-25T12:15:30");
        final var timeInSeconds = localDateTime.toEpochSecond(ZoneOffset.UTC);
        final var createdDate = Instant.ofEpochSecond(timeInSeconds);
        final var lastUpdatedDate = Instant.ofEpochSecond(timeInSeconds);
        final var result =
                Review.of(null,
                        description,
                        title,
                        rating,
                        userId,
                        createdDate,
                        lastUpdatedDate
                ).getLeft();
        assertAll(
                () -> assertEquals(1,result.size()),
                () -> assertTrue(result.stream().allMatch(it -> it instanceof Review.ReviewError.DescriptionError))
        );
    }

    @Test
    @DisplayName("title must not be empty")
    void review_title_must_not_be_empty() {
        final var description = "description";
        final var title = "";
        final var rating = 5;
        final var userId = "userId";
        final var localDateTime = LocalDateTime.parse("2019-10-25T12:15:30");
        final var timeInSeconds = localDateTime.toEpochSecond(ZoneOffset.UTC);
        final var createdDate = Instant.ofEpochSecond(timeInSeconds);
        final var lastUpdatedDate = Instant.ofEpochSecond(timeInSeconds);
        final var result =
                Review.of(null,
                        description,
                        title,
                        rating,
                        userId,
                        createdDate,
                        lastUpdatedDate
                ).getLeft();
        assertAll(
                () -> assertEquals(1,result.size()),
                () -> assertTrue(result.stream().allMatch(it -> it instanceof Review.ReviewError.TitleError))
        );
    }

    @Test
    @DisplayName("rating must not be negative")
    void review_rating_must_not_be_negative() {
        final var description = "description";
        final var title = "title";
        final var rating = -2;
        final var userId = "userId";
        final var localDateTime = LocalDateTime.parse("2019-10-25T12:15:30");
        final var timeInSeconds = localDateTime.toEpochSecond(ZoneOffset.UTC);
        final var createdDate = Instant.ofEpochSecond(timeInSeconds);
        final var lastUpdatedDate = Instant.ofEpochSecond(timeInSeconds);
        final var result =
                Review.of(null,
                        description,
                        title,
                        rating,
                        userId,
                        createdDate,
                        lastUpdatedDate
                ).getLeft();
        assertAll(
                () -> assertEquals(1,result.size()),
                () -> assertTrue(result.stream().allMatch(it -> it instanceof Review.ReviewError.RatingError))
        );
    }

    @Test
    @DisplayName("userId must not be negative")
    void review_userId_must_not_be_negative() {
        final var description = "description";
        final var title = "title";
        final var rating = 5;
        final var userId = "";
        final var localDateTime = LocalDateTime.parse("2019-10-25T12:15:30");
        final var timeInSeconds = localDateTime.toEpochSecond(ZoneOffset.UTC);
        final var createdDate = Instant.ofEpochSecond(timeInSeconds);
        final var lastUpdatedDate = Instant.ofEpochSecond(timeInSeconds);
        final var result =
                Review.of(null,
                        description,
                        title,
                        rating,
                        userId,
                        createdDate,
                        lastUpdatedDate
                ).getLeft();
        assertAll(
                () -> assertEquals(1,result.size()),
                () -> assertTrue(result.stream().allMatch(it -> it instanceof Review.ReviewError.UserIdError))
        );
    }

    @Test
    @DisplayName("createdDate must not be greater than today")
    void review_createdDate_must_not_be_greater_than_today() {
        final var description = "description";
        final var title = "title";
        final var rating = 5;
        final var userId = "userId";
        final var createdDate = Instant.ofEpochSecond(
                LocalDateTime.now().plusDays(5).toEpochSecond(ZoneOffset.UTC));
        final var lastUpdatedDate = Instant.ofEpochSecond(
                LocalDateTime.now().minusDays(1).toEpochSecond(ZoneOffset.UTC));
        final var result =
                Review.of(null,
                        description,
                        title,
                        rating,
                        userId,
                        createdDate,
                        lastUpdatedDate
                ).getLeft();
        result.stream().forEach(System.out::println);
        assertAll(
                () -> assertEquals(2,result.size()),
                () -> assertTrue(result.stream().anyMatch(it -> it instanceof Review.ReviewError.CreatedDateError)),
                () -> assertTrue(result.stream().anyMatch(it -> it instanceof Review.ReviewError.LastUpdatedDateGreaterThanCreatedDateError))
        );
    }

    @Test
    @DisplayName("lastUpdatedDate must not be in the future")
    void lastUpdatedDate_must_not_be_in_the_future() {
        final var description = "description";
        final var title = "title";
        final var rating = 5;
        final var userId = "userId";
        final var createdDate = Instant.ofEpochSecond(
                LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        final var lastUpdatedDate = Instant.ofEpochSecond(
                LocalDateTime.now().plusDays(5).minusDays(1).toEpochSecond(ZoneOffset.UTC));
        final var result =
                Review.of(null,
                        description,
                        title,
                        rating,
                        userId,
                        createdDate,
                        lastUpdatedDate
                ).getLeft();
        result.stream().forEach(System.out::println);
        assertAll(
                () -> assertEquals(2,result.size()),
                () -> assertTrue(result.stream().anyMatch(it -> it instanceof Review.ReviewError.CreatedDateError)),
                () -> assertTrue(result.stream().anyMatch(it -> it instanceof Review.ReviewError.LastUpdatedDateError))
        );
    }

    @Test
    @DisplayName("lastUpdatedDate must not be greater than today")
    void review_lastUpdatedDate_must_not_be_greater_than_today() {
        final var description = "description";
        final var title = "title";
        final var rating = 5;
        final var userId = "userId";
        final var localDateTime = LocalDateTime.parse("2015-10-25T12:15:30");
        final var timeInSeconds = localDateTime.toEpochSecond(ZoneOffset.UTC);
        final var lastUpdatedDate = Instant.ofEpochSecond(timeInSeconds);
        final var localDateTime2 = LocalDateTime.parse("2020-10-25T12:15:30");
        final var timeInSeconds2 = localDateTime2.toEpochSecond(ZoneOffset.UTC);
        final var createdDate = Instant.ofEpochSecond(timeInSeconds2);
        final var result =
                Review.of(null,
                        description,
                        title,
                        rating,
                        userId,
                        createdDate,
                        lastUpdatedDate
                ).getLeft();
        assertAll(
                () -> assertEquals(1,result.size()),
                () -> assertTrue(result.stream().allMatch(it -> it instanceof Review.ReviewError.LastUpdatedDateGreaterThanCreatedDateError))
        );
    }
}