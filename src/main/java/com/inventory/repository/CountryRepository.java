package com.inventory.repository;

import com.inventory.model.db.country.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends CrudRepository<Country, String> {
    @Query(value = "SELECT * FROM Country b WHERE LOWER(b.country_id) = LOWER(:countryId) AND b.status = :status",
            nativeQuery = true)
    Optional<Country> findByCountryIdAndStatus(String countryId, String status);

    @Query(value = "SELECT * FROM Country b WHERE LOWER(b.country_name) = LOWER(:countryName) AND b.status = :status",
            nativeQuery = true)
    Optional<Country> findByCountryNameAndStatus(String countryName, String status);
}
