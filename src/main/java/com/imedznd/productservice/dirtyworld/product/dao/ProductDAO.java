package com.imedznd.productservice.dirtyworld.product.dao;

import com.imedznd.productservice.cleanworld.product.model.Product;
import com.imedznd.productservice.cleanworld.review.model.Review;
import io.vavr.control.Either;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;
import java.util.Set;

@Document(collection = "product")
public record ProductDAO(
        @Id
        String id,
        String name,
        String description,
        BigDecimal price,
        String category,
        String imgUrl,
        int saleCounter,
        int rating,
        Product.ProductStatus status,
        Set<Review> reviews,
        Instant createdDate,
        Instant lastUpdatedDate
) {
    public static ProductDAO toDAO(Product product){
        return new ProductDAO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory(),
                product.getImgUrl(),
                product.getSaleCounter(),
                product.getRating(),
                product.getStatus(),
                product.getReviews(),
                product.getCreatedDate(),
                product.getLastUpdatedDate()
        );
    }

    public static Either<Collection<? extends Product.ProductError>, Product> toModel(ProductDAO productDAO){
        return Product.of(
                productDAO.id,
                productDAO.name,
                productDAO.description,
                productDAO.price,
                productDAO.category,
                productDAO.imgUrl,
                productDAO.saleCounter,
                productDAO.rating,
                productDAO.status,
                productDAO.reviews,
                productDAO.createdDate,
                productDAO.lastUpdatedDate
        );
    }
}
