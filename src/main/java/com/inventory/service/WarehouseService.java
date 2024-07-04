package com.inventory.service;

import com.inventory.dto.request.warehouse.WarehouseRequest;
import com.inventory.dto.response.warehouse.WarehouseAddResponse;

public interface WarehouseService {
    WarehouseAddResponse addWarehouse(WarehouseRequest request);
}