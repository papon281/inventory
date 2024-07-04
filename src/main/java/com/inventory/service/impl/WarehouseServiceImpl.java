package com.inventory.service.impl;

import com.inventory.constant.enums.Status;
import com.inventory.dto.request.warehouse.WarehouseRequest;
import com.inventory.dto.response.warehouse.WarehouseAddResponse;
import com.inventory.exception.ExistException;
import com.inventory.exception.NotFoundException;
import com.inventory.model.db.user.User;
import com.inventory.model.db.warehouse.Warehouse;
import com.inventory.repository.UserRepository;
import com.inventory.repository.WarehouseRepository;
import com.inventory.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    private static final Logger logger = Logger.getLogger(WarehouseServiceImpl.class.getName());
    private final WarehouseRepository warehouseRepository;
    private final UserRepository userRepository;

    @Autowired
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository, UserRepository userRepository) {
        this.warehouseRepository = warehouseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public WarehouseAddResponse addWarehouse(WarehouseRequest request) {
        checkDuplicateWarehouse(request.getWarehouseId(), request.getWarehouseName());
        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseId(request.getWarehouseId());
        warehouse.setWarehouseName(request.getWarehouseName());
        warehouse.setStatus(Status.ACTIVE);
        warehouse.setCreatedBy(getUser(request.getEmail()));
        warehouseRepository.save(warehouse);
        logger.info("Warehouse data save success: " + warehouse);
        return WarehouseAddResponse.from(warehouse);
    }

    private void checkDuplicateWarehouse(String warehouseId, String warehouseName) {
        Optional<Warehouse> duplicateById = warehouseRepository.findByWarehouseIdAndStatus(warehouseId,
                String.valueOf(Status.ACTIVE));
        Optional<Warehouse> duplicateByName = warehouseRepository.findByWarehouseNameAndStatus(warehouseName,
                String.valueOf(Status.ACTIVE));
        if (duplicateById.isPresent()) {
            throw new ExistException("Warehouse Id is exist: " + warehouseId);
        }
        if (duplicateByName.isPresent()) {
            throw new ExistException("Warehouse Name is exist: " + warehouseName);
        }
    }

    private User getUser(String email) {
        Optional<User> optionalUser = userRepository.findByEmailAndStatus(email, Status.ACTIVE);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User not found: " + email);
        }
        return optionalUser.get();
    }
}