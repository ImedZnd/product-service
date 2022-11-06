package com.imedznd.productservice.cleanworld.product.model;

import com.imedznd.productservice.cleanworld.review.model.Review;
import io.vavr.control.Either;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder
public final class Product {

    private final String name;
    private final String description;
    private final BigDecimal price;
    private final String category;
    private final String imgUrl;
    private final int saleCounter;
    private final int rating;
    private final String status;
    private final Set<Review> reviews;
    private final Instant createdDate;
    private final Instant lastUpdatedDate;

    private Product(
            final String name,
            final String description,
            final BigDecimal price,
            final String category,
            final String imgUrl,
            final int saleCounter,
            final int rating,
            final String status,
            final Set<Review> reviews,
            final Instant createdDate,
            final Instant lastUpdatedDate
    ) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.saleCounter = saleCounter;
        this.rating = rating;
        this.status = status;
        this.reviews = reviews;
        this.createdDate = createdDate;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public static Either<Collection<? extends ProductError>, Product> of(
            final String name,
            final String description,
            final BigDecimal price,
            final String category,
            final String imgUrl,
            final int saleCounter,
            final int rating,
            final String status,
            final Set<Review> reviews,
            final Instant createdDate,
            final Instant lastUpdatedDate
    ) {
        final var checkResult =
                checkInput(
                        name,
                        description,
                        price,
                        category,
                        imgUrl,
                        saleCounter,
                        rating,
                        status,
                        reviews,
                        createdDate,
                        lastUpdatedDate
                );
        return
                checkResult.isEmpty()
                        ?
                        Either.right(
                                new Product(
                                        name,
                                        description,
                                        price,
                                        category,
                                        imgUrl,
                                        saleCounter,
                                        rating,
                                        status,
                                        reviews,
                                        createdDate,
                                        lastUpdatedDate
                                )
                        ) :
                        Either.left(checkResult);
    }

    private static Collection<? extends ProductError> checkInput(
            final String name,
            final String description,
            final BigDecimal price,
            final String category,
            final String imgUrl,
            final int saleCounter,
            final int rating,
            final String status,
            final Set<Review> reviews,
            final Instant createdDate,
            final Instant lastUpdatedDate
    ) {
        return
                Stream.of(
                                checkName(name),
                                checkDescription(description),
                                checkPrice(price),
                                checkCategory(category),
                                checkImgUrl(imgUrl),
                                checkSalesCounter(saleCounter),
                                checkRating(rating),
                                checkStatus(status),
                                checkReviews(reviews),
                                checkCreatedDate(createdDate),
                                checkLastUpdatedDate(lastUpdatedDate)
                        )
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toUnmodifiableSet());
    }

    private static Optional<? extends ProductError> checkCreatedDate(Instant createdDate) {
        return
                checkInstantOrError(
                        createdDate,
                        ProductError.CreatedDateError::new
                );
    }

    private static Optional<? extends ProductError> checkLastUpdatedDate(Instant lastUpdatedDate) {
        return
                checkInstantOrError(
                        lastUpdatedDate,
                        ProductError.LastUpdatedDateError::new
                );
    }

    private static Optional<? extends ProductError> checkInstantOrError(
            Instant instant,
            Supplier<? extends ProductError> supplierError
    ) {
        return checkInstant(instant)
                ? Optional.empty()
                : Optional.of(supplierError.get());
    }

    private static boolean checkInstant(Instant instant) {
        return instant.compareTo(Instant.now()) > 0;
    }

    private static Optional<? extends ProductError> checkReviews(Set<Review> reviews) {
        return
                checkSetOfReviewsOrError(
                        reviews,
                        ProductError.ReviewsError::new
                );
    }

    private static Optional<? extends ProductError> checkSetOfReviewsOrError(
            Set<Review> setOfReviews,
            Supplier<? extends ProductError> errorSupplier
    ) {
        return checkSetOfReviews(setOfReviews)
                ? Optional.empty()
                : Optional.of(errorSupplier.get());
    }

    private static boolean checkSetOfReviews(Set<Review> reviews) {
        return
                reviews.stream()
                        .noneMatch(Objects::nonNull);
    }

    private static Optional<? extends ProductError> checkSalesCounter(int saleCounter) {
        return
                checkIntOrError(
                        saleCounter,
                        ProductError.SalesCounterError::new
                );
    }

    private static Optional<? extends ProductError> checkRating(int rating) {
        return
                checkIntOrError(
                        rating,
                        ProductError.RatingError::new
                );
    }

    private static Optional<? extends ProductError> checkIntOrError(
            int integer,
            Supplier<? extends ProductError> errorSupplier
    ) {
        return
                checkInteger(integer)
                        ? Optional.empty()
                        : Optional.of(errorSupplier.get());
    }

    private static boolean checkInteger(int integer) {
        return
                Objects.nonNull(integer) &&
                        checkIntegerNotNegative(integer);
    }

    private static boolean checkIntegerNotNegative(int integer) {
        return
                integer < 0;
    }

    private static Optional<? extends ProductError> checkPrice(BigDecimal price) {
        return
                checkBigDecimalOrError(
                        price,
                        ProductError.PriceError::new
                );
    }

    private static Optional<? extends ProductError> checkBigDecimalOrError(
            final BigDecimal bigDecimal,
            final Supplier<? extends ProductError> errorSupplier
    ) {
        return
                checkBigDecimal(bigDecimal)
                        ? Optional.empty()
                        : Optional.of(errorSupplier.get());
    }

    private static boolean checkBigDecimal(BigDecimal bigDecimal) {
        return
                Objects.nonNull(bigDecimal) &&
                        checkBigDecimalNotNegative(bigDecimal);
    }

    private static boolean checkBigDecimalNotNegative(BigDecimal bigDecimal) {
        return
                bigDecimal.compareTo(BigDecimal.valueOf(0)) >= 0;
    }

    private static Optional<? extends ProductError> checkName(final String name) {
        return
                checkStringOrError(
                        name,
                        ProductError.NameError::new
                );
    }

    private static Optional<? extends ProductError> checkDescription(final String description) {
        return
                checkStringOrError(
                        description,
                        ProductError.DescriptionError::new
                );
    }

    private static Optional<? extends ProductError> checkCategory(final String category) {
        return
                checkStringOrError(
                        category,
                        ProductError.CategoryError::new
                );
    }

    private static Optional<? extends ProductError> checkImgUrl(final String imgUrl) {
        return
                checkStringOrError(
                        imgUrl,
                        ProductError.ImgUrlError::new
                );
    }

    private static Optional<? extends ProductError> checkStatus(final String status) {
        return
                checkStringOrError(
                        status,
                        ProductError.StatusError::new
                );
    }

    private static Optional<? extends ProductError> checkStringOrError(
            final String string,
            final Supplier<? extends ProductError> errorSupplier
    ) {
        return
                checkString(string)
                        ? Optional.empty()
                        : Optional.of(errorSupplier.get());
    }

    private static boolean checkString(String string) {
        return
                Objects.nonNull(string) &&
                        checkStringNotEmpty(string);
    }

    private static boolean checkStringNotEmpty(String string) {
        return !string.isEmpty();
    }

    public sealed interface ProductError {

        String message();

        record NameError(String message) implements ProductError {
            public NameError() {
                this("");
            }
        }

        record DescriptionError(String message) implements ProductError {
            public DescriptionError() {
                this("");
            }
        }

        record CategoryError(String message) implements ProductError {
            public CategoryError() {
                this("");
            }
        }

        record ImgUrlError(String message) implements ProductError {
            public ImgUrlError() {
                this("");
            }
        }

        record PriceError(String message) implements ProductError {
            public PriceError() {
                this("");
            }
        }

        record SalesCounterError(String message) implements ProductError {
            public SalesCounterError() {
                this("");
            }
        }

        record StatusError(String message) implements ProductError {
            public StatusError() {
                this("");
            }
        }

        record ReviewsError(String message) implements ProductError {
            public ReviewsError() {
                this("");
            }
        }

        record RatingError(String message) implements ProductError {
            public RatingError() {
                this("");
            }
        }

        record LastUpdatedDateError(String message) implements ProductError {
            public LastUpdatedDateError() {
                this("");
            }
        }

        record CreatedDateError(String message) implements ProductError {
            public CreatedDateError() {
                this("");
            }
        }
    }
}
