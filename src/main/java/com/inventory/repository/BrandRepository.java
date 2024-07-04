package com.inventory.repository;

import com.inventory.model.db.brand.Brand;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends CrudRepository<Brand, String> {
    @Query(value = "SELECT * FROM Brand b WHERE LOWER(b.brand_id) = LOWER(:brandId) AND b.status = :status",
            nativeQuery = true)
    Optional<Brand> findByBrandIdAndStatus(String brandId, String status);

    @Query(value = "SELECT * FROM Brand b WHERE LOWER(b.brand_name) = LOWER(:brandName) AND b.status = :status",
            nativeQuery = true)
    Optional<Brand> findByBrandNameAndStatus(String brandName, String status);
}