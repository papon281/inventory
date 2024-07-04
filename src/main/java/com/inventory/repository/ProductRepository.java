package com.inventory.repository;

import com.inventory.model.db.product.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {
    @Query(value = "SELECT * FROM Product b WHERE LOWER(b.product_id) = LOWER(:productId) AND b.status = :status",
            nativeQuery = true)
    Optional<Product> findByProductIdAndStatus(String productId, String status);

    @Query(value = "SELECT * FROM Product b WHERE LOWER(b.product_name) = LOWER(:productName) AND b.status = :status",
            nativeQuery = true)
    Optional<Product> findByProductNameAndStatus(String productName, String status);
}