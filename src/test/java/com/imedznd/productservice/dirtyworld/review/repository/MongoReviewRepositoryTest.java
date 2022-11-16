package com.imedznd.productservice.dirtyworld.review.repository;

import com.imedznd.productservice.cleanworld.review.model.Review;
import com.imedznd.productservice.cleanworld.review.repository.ReviewRepository;
import com.imedznd.productservice.dirtyworld.initilizer.Initializer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@SpringBootTest
@DirtiesContext
@ContextConfiguration(initializers = {Initializer.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MongoReviewRepositoryTest {

    private final MongoReviewRepository mongoReviewRepository;
    private final ReactiveMongodbReviewRepository reactiveMongodbReviewRepository;

    MongoReviewRepositoryTest(
            MongoReviewRepository mongoReviewRepository,
            ReactiveMongodbReviewRepository reactiveMongodbReviewRepository
    ) {
        this.mongoReviewRepository = mongoReviewRepository;
        this.reactiveMongodbReviewRepository = reactiveMongodbReviewRepository;
    }

    @BeforeAll
    public void beforeAll() {
        reactiveMongodbReviewRepository.deleteAll();
    }

    @BeforeEach
    public void beforeEach() {
        reactiveMongodbReviewRepository.deleteAll();
    }

    @AfterEach
    public void afterEach() {
        reactiveMongodbReviewRepository.deleteAll();
    }

    @AfterAll
    public void afterAll() {
        reactiveMongodbReviewRepository.deleteAll();
    }

    @Test
    @DisplayName("saveCar: save null car must not be valid")
    void save_null_car_must_not_be_valid() {
        final var description = "description";
        final var title = "title";
        final var rating = 5;
        final var userId = "userId";
        final var localDateTime = LocalDateTime.parse("2019-10-25T12:15:30");
        final var timeInSeconds = localDateTime.toEpochSecond(ZoneOffset.UTC);
        final var createdDate = Instant.ofEpochSecond(timeInSeconds);
        final var lastUpdatedDate = Instant.ofEpochSecond(timeInSeconds);
        final var review =
                Review.of(null,
                        description,
                        title,
                        rating,
                        userId,
                        createdDate,
                        lastUpdatedDate
                ).get();
        final var result =
                mongoReviewRepository.save(review);
         Assertions.assertTrue(result instanceof Mono<Review>);
    }

}