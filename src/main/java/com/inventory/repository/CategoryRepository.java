package com.inventory.repository;

import com.inventory.model.db.category.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, String> {
    @Query(value = "SELECT * FROM Category b WHERE LOWER(b.category_id) = LOWER(:categoryId) AND b.status = :status",
            nativeQuery = true)
    Optional<Category> findByCategoryIdAndStatus(String categoryId, String status);

    @Query(value = "SELECT * FROM Category b WHERE LOWER(b.category_name) = LOWER(:categoryName) AND b.status = :status",
            nativeQuery = true)
    Optional<Category> findByCategoryNameAndStatus(String categoryName, String status);
}