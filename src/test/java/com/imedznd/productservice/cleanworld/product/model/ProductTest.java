package com.imedznd.productservice.cleanworld.product.model;

import com.imedznd.productservice.cleanworld.review.model.Review;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    @DisplayName("product should be initiated with initial fields")
    void product_should_be_initiated_with_initial_fields() {
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
        final var name = "name";
        final var productDescription = "productDescription";
        final var price = BigDecimal.valueOf(1200);
        final var category = "category";
        final var imgUrl = "imgUrl";
        final var saleCounter = 5;
        final var productRating = 5;
        final var status = Product.ProductStatus.AVAILABLE;

    }
}