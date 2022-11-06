package com.imedznd.productservice.cleanworld.review.model;

import com.imedznd.productservice.cleanworld.product.model.Product;
import io.vavr.control.Either;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder
public class Review {
    private final String description;
    private final String title;
    private final int rating;
    private final String userId;
    private final Instant createdDate;
    private final Instant lastUpdatedDate;

    private Review(
            final String description,
            final String title,
            final int rating,
            final String userId,
            final Instant createdDate,
            final Instant lastUpdatedDate

    ) {
        this.description = description;
        this.rating = rating;
        this.title = title;
        this.userId = userId;
        this.createdDate = createdDate;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public static Either<Collection<? extends ReviewError>, Review> of(
            final String description,
            final String title,
            final int rating,
            final String userId,
            final Instant createdDate,
            final Instant lastUpdatedDate
    ) {
        final var checkResult =
                checkInput(
                        description,
                        title,
                        rating,
                        userId,
                        createdDate,
                        lastUpdatedDate
                );
        return
                checkResult.isEmpty()
                        ?
                        Either.right(
                                new Review(
                                        description,
                                        title,
                                        rating,
                                        userId,
                                        createdDate,
                                        lastUpdatedDate
                                )
                        )
                        :
                        Either.left(checkResult);
    }

    private static Collection<? extends ReviewError> checkInput(
            final String description,
            final String title,
            final int rating,
            final String userId,
            final Instant createdDate,
            final Instant lastUpdatedDate
    ) {
        return
                Stream.of(
                                checkTitle(title),
                                checkDescription(description),
                                checkUserId(userId),
                                checkRating(rating),
                                checkCreatedDate(createdDate),
                                checkLastUpdatedDate(lastUpdatedDate)
                        )
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toUnmodifiableSet());
    }

    private static Optional<? extends ReviewError> checkCreatedDate(Instant createdDate) {
        return
                checkInstantOrError(
                        createdDate,
                        ReviewError.CreatedDateError::new
                );
    }

    private static Optional<? extends ReviewError> checkLastUpdatedDate(Instant lastUpdatedDate) {
        return
                checkInstantOrError(
                        lastUpdatedDate,
                        ReviewError.LastUpdatedDateError::new
                );
    }

    private static Optional<? extends ReviewError> checkInstantOrError(
            Instant instant,
            Supplier<? extends ReviewError> supplierError
    ) {
        return checkInstant(instant)
                ? Optional.empty()
                : Optional.of(supplierError.get());
    }

    private static boolean checkInstant(Instant instant) {
        return instant.compareTo(Instant.now()) < 0;
    }

    private static Optional<? extends ReviewError> checkRating(int rating) {
        return
                checkIntOrError(
                        rating,
                        ReviewError.RatingError::new
                );
    }

    private static Optional<? extends ReviewError> checkIntOrError(
            int integer,
            Supplier<? extends ReviewError> errorSupplier
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
                integer > 0;
    }

    private static Optional<? extends ReviewError> checkUserId(final String description) {
        return
                checkStringOrError(
                        description,
                        ReviewError.UserIdError::new
                );
    }

    private static Optional<? extends ReviewError> checkDescription(final String description) {
        return
                checkStringOrError(
                        description,
                        ReviewError.DescriptionError::new
                );
    }

    private static Optional<? extends ReviewError> checkTitle(final String description) {
        return
                checkStringOrError(
                        description,
                        ReviewError.TitleError::new
                );
    }

    private static Optional<? extends ReviewError> checkStringOrError(
            final String string,
            final Supplier<? extends ReviewError> errorSupplier
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

    public sealed interface ReviewError {

        String message();

        record TitleError(String message) implements ReviewError {
            public TitleError() {
                this("");
            }
        }

        record DescriptionError(String message) implements ReviewError {
            public DescriptionError() {
                this("");
            }
        }

        record RatingError(String message) implements ReviewError {
            public RatingError() {
                this("");
            }
        }

        record UserIdError(String message) implements ReviewError {
            public UserIdError() {
                this("");
            }
        }

        record CreatedDateError(String message) implements ReviewError {
            public CreatedDateError() {
                this("");
            }
        }

        record LastUpdatedDateError(String message) implements ReviewError {
            public LastUpdatedDateError() {
                this("");
            }
        }

    }
}
