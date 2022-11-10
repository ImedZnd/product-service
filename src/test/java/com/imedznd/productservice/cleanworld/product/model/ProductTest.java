package com.imedznd.productservice.cleanworld.product.model;

import com.imedznd.productservice.cleanworld.review.model.Review;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    @DisplayName("should be initiated with initial fields")
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
        final var reviews = Set.of(review);
        final var productCreatedDate = Instant.ofEpochSecond(
                LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC));
        final var productLastUpdatedDate = Instant.ofEpochSecond(
                LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC));
        final var result =
                Product.of(
                        null,
                        name,
                        productDescription,
                        price,
                        category,
                        imgUrl,
                        saleCounter,
                        productRating,
                        status,
                        reviews,
                        productCreatedDate,
                        productLastUpdatedDate
                ).get();
        assertAll(
                () -> assertEquals(productDescription, result.getDescription()),
                () -> assertEquals(category, result.getCategory()),
                () -> assertEquals(productRating, result.getRating()),
                () -> assertEquals(name, result.getName()),
                () -> assertEquals(price, result.getPrice()),
                () -> assertEquals(saleCounter, result.getSaleCounter()),
                () -> assertEquals(status, result.getStatus()),
                () -> assertEquals(reviews, result.getReviews()),
                () -> assertEquals(productCreatedDate, result.getCreatedDate()),
                () -> assertEquals(productLastUpdatedDate, result.getLastUpdatedDate()),
                () -> assertEquals(imgUrl, result.getImgUrl())
        );
    }

    @Test
    @DisplayName("description should not be empty")
    void product_description_should_not_be_empty() {
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
        final var productDescription = "";
        final var price = BigDecimal.valueOf(1200);
        final var category = "category";
        final var imgUrl = "imgUrl";
        final var saleCounter = 5;
        final var productRating = 5;
        final var status = Product.ProductStatus.AVAILABLE;
        final var reviews = Set.of(review);
        final var productCreatedDate = Instant.ofEpochSecond(
                LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC));
        final var productLastUpdatedDate = Instant.ofEpochSecond(
                LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC));
        final var result =
                Product.of(
                        null,
                        name,
                        productDescription,
                        price,
                        category,
                        imgUrl,
                        saleCounter,
                        productRating,
                        status,
                        reviews,
                        productCreatedDate,
                        productLastUpdatedDate
                ).getLeft();
        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertTrue(result.stream().allMatch(it -> it instanceof Product.ProductError.DescriptionError))
        );
    }

    @Test
    @DisplayName("name should not be empty")
    void product_name_should_not_be_empty() {
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
        final var name = "";
        final var productDescription = "productDescription";
        final var price = BigDecimal.valueOf(1200);
        final var category = "category";
        final var imgUrl = "imgUrl";
        final var saleCounter = 5;
        final var productRating = 5;
        final var status = Product.ProductStatus.AVAILABLE;
        final var reviews = Set.of(review);
        final var productCreatedDate = Instant.ofEpochSecond(
                LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC));
        final var productLastUpdatedDate = Instant.ofEpochSecond(
                LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC));
        final var result =
                Product.of(
                        null,
                        name,
                        productDescription,
                        price,
                        category,
                        imgUrl,
                        saleCounter,
                        productRating,
                        status,
                        reviews,
                        productCreatedDate,
                        productLastUpdatedDate
                ).getLeft();
        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertTrue(result.stream().allMatch(it -> it instanceof Product.ProductError.NameError))
        );
    }

    @Test
    @DisplayName("price should not be negative")
    void product_price_should_not_be_negative() {
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
        final var price = BigDecimal.valueOf(-1520);
        final var category = "category";
        final var imgUrl = "imgUrl";
        final var saleCounter = 5;
        final var productRating = 5;
        final var status = Product.ProductStatus.AVAILABLE;
        final var reviews = Set.of(review);
        final var productCreatedDate = Instant.ofEpochSecond(
                LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC));
        final var productLastUpdatedDate = Instant.ofEpochSecond(
                LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC));
        final var result =
                Product.of(
                        null,
                        name,
                        productDescription,
                        price,
                        category,
                        imgUrl,
                        saleCounter,
                        productRating,
                        status,
                        reviews,
                        productCreatedDate,
                        productLastUpdatedDate
                ).getLeft();
        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertTrue(result.stream().allMatch(it -> it instanceof Product.ProductError.PriceError))
        );
    }

    @Test
    @DisplayName("price should not be null")
    void product_price_should_not_be_null() {
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
        final BigDecimal price = null;
        final var category = "category";
        final var imgUrl = "imgUrl";
        final var saleCounter = 5;
        final var productRating = 5;
        final var status = Product.ProductStatus.AVAILABLE;
        final var reviews = Set.of(review);
        final var productCreatedDate = Instant.ofEpochSecond(
                LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC));
        final var productLastUpdatedDate = Instant.ofEpochSecond(
                LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC));
        final var result =
                Product.of(
                        null,
                        name,
                        productDescription,
                        price,
                        category,
                        imgUrl,
                        saleCounter,
                        productRating,
                        status,
                        reviews,
                        productCreatedDate,
                        productLastUpdatedDate
                ).getLeft();
        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertTrue(result.stream().allMatch(it -> it instanceof Product.ProductError.PriceError))
        );
    }

    @Test
    @DisplayName("category should not be empty")
    void product_category_should_not_be_empty() {
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
        final var category = "";
        final var imgUrl = "imgUrl";
        final var saleCounter = 5;
        final var productRating = 5;
        final var status = Product.ProductStatus.AVAILABLE;
        final var reviews = Set.of(review);
        final var productCreatedDate = Instant.ofEpochSecond(
                LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC));
        final var productLastUpdatedDate = Instant.ofEpochSecond(
                LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC));
        final var result =
                Product.of(
                        null,
                        name,
                        productDescription,
                        price,
                        category,
                        imgUrl,
                        saleCounter,
                        productRating,
                        status,
                        reviews,
                        productCreatedDate,
                        productLastUpdatedDate
                ).getLeft();
        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertTrue(result.stream().allMatch(it -> it instanceof Product.ProductError.CategoryError))
        );
    }

    @Test
    @DisplayName("imgUrl should not be empty")
    void product_imgUrl_should_not_be_empty() {
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
        final var imgUrl = "";
        final var saleCounter = 5;
        final var productRating = 5;
        final var status = Product.ProductStatus.AVAILABLE;
        final var reviews = Set.of(review);
        final var productCreatedDate = Instant.ofEpochSecond(
                LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC));
        final var productLastUpdatedDate = Instant.ofEpochSecond(
                LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC));
        final var result =
                Product.of(
                        null,
                        name,
                        productDescription,
                        price,
                        category,
                        imgUrl,
                        saleCounter,
                        productRating,
                        status,
                        reviews,
                        productCreatedDate,
                        productLastUpdatedDate
                ).getLeft();
        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertTrue(result.stream().allMatch(it -> it instanceof Product.ProductError.ImgUrlError))
        );
    }

    @Test
    @DisplayName("saleCounter should not be negative")
    void product_saleCounter_should_not_be_negative() {
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
        final var saleCounter = -2;
        final var productRating = 5;
        final var status = Product.ProductStatus.AVAILABLE;
        final var reviews = Set.of(review);
        final var productCreatedDate = Instant.ofEpochSecond(
                LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC));
        final var productLastUpdatedDate = Instant.ofEpochSecond(
                LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC));
        final var result =
                Product.of(
                        null,
                        name,
                        productDescription,
                        price,
                        category,
                        imgUrl,
                        saleCounter,
                        productRating,
                        status,
                        reviews,
                        productCreatedDate,
                        productLastUpdatedDate
                ).getLeft();
        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertTrue(result.stream().allMatch(it -> it instanceof Product.ProductError.SalesCounterError))
        );
    }

    @Test
    @DisplayName("productRating should not be negative")
    void product_productRating_should_not_be_negative() {
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
        final var saleCounter = 10;
        final var productRating = -5;
        final var status = Product.ProductStatus.AVAILABLE;
        final var reviews = Set.of(review);
        final var productCreatedDate = Instant.ofEpochSecond(
                LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC));
        final var productLastUpdatedDate = Instant.ofEpochSecond(
                LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC));
        final var result =
                Product.of(
                        null,
                        name,
                        productDescription,
                        price,
                        category,
                        imgUrl,
                        saleCounter,
                        productRating,
                        status,
                        reviews,
                        productCreatedDate,
                        productLastUpdatedDate
                ).getLeft();
        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertTrue(result.stream().allMatch(it -> it instanceof Product.ProductError.RatingError))
        );
    }

    @Test
    @DisplayName("reviews should not be null")
    void product_reviews_should_not_be_null() {
        final var name = "name";
        final var productDescription = "productDescription";
        final var price = BigDecimal.valueOf(1200);
        final var category = "category";
        final var imgUrl = "imgUrl";
        final var saleCounter = 10;
        final var productRating = 5;
        final var status = Product.ProductStatus.AVAILABLE;
        final Set<Review> reviews = null;
        final var productCreatedDate = Instant.ofEpochSecond(
                LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC));
        final var productLastUpdatedDate = Instant.ofEpochSecond(
                LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC));
        final var result =
                Product.of(
                        null,
                        name,
                        productDescription,
                        price,
                        category,
                        imgUrl,
                        saleCounter,
                        productRating,
                        status,
                        reviews,
                        productCreatedDate,
                        productLastUpdatedDate
                ).getLeft();
        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertTrue(result.stream().allMatch(it -> it instanceof Product.ProductError.ReviewsError))
        );
    }

    @Test
    @DisplayName("CreatedDate should not be in the future")
    void createdDate_should_not_be_in_the_future() {
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
        final var saleCounter = 10;
        final var productRating = 5;
        final var status = Product.ProductStatus.AVAILABLE;
        final var reviews = Set.of(review);
        final var productCreatedDate = Instant.ofEpochSecond(
                LocalDateTime.now().plusDays(5).toEpochSecond(ZoneOffset.UTC));
        final var productLastUpdatedDate = Instant.ofEpochSecond(
                LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        final var result =
                Product.of(
                        null,
                        name,
                        productDescription,
                        price,
                        category,
                        imgUrl,
                        saleCounter,
                        productRating,
                        status,
                        reviews,
                        productCreatedDate,
                        productLastUpdatedDate
                ).getLeft();
        result.stream().forEach(System.out::println);
        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertTrue(result.stream().anyMatch(it -> it instanceof Product.ProductError.CreatedDateError)),
                () -> assertTrue(result.stream().anyMatch(it -> it instanceof Product.ProductError.LastUpdatedDateError))
        );
    }

    @Test
    @DisplayName("LastUpdatedDate should not be in the future")
    void LastUpdatedDate_should_not_be_in_the_future() {
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
        final var saleCounter = 10;
        final var productRating = 5;
        final var status = Product.ProductStatus.AVAILABLE;
        final var reviews = Set.of(review);
        final var productCreatedDate = Instant.ofEpochSecond(
                LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC));
        final var productLastUpdatedDate = Instant.ofEpochSecond(
                LocalDateTime.now().plusDays(5).toEpochSecond(ZoneOffset.UTC));
        final var result =
                Product.of(
                        null,
                        name,
                        productDescription,
                        price,
                        category,
                        imgUrl,
                        saleCounter,
                        productRating,
                        status,
                        reviews,
                        productCreatedDate,
                        productLastUpdatedDate
                ).getLeft();
        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertTrue(result.stream().anyMatch(it -> it instanceof Product.ProductError.LastUpdatedDateError))
        );
    }

}