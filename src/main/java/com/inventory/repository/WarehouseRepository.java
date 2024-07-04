package com.inventory.repository;

import com.inventory.model.db.warehouse.Warehouse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WarehouseRepository extends CrudRepository<Warehouse, String> {
    @Query(value = "SELECT * FROM Warehouse b WHERE LOWER(b.warehouse_id) = LOWER(:warehouseId) AND b.status = :status",
            nativeQuery = true)
    Optional<Warehouse> findByWarehouseIdAndStatus(String warehouseId, String status);

    @Query(value = "SELECT * FROM Warehouse b WHERE LOWER(b.warehouse_name) = LOWER(:warehouseName) AND b.status = :status",
            nativeQuery = true)
    Optional<Warehouse> findByWarehouseNameAndStatus(String warehouseName, String status);
}