package com.inventory.controller;

import com.inventory.dto.request.product.ProductRequest;
import com.inventory.dto.request.warehouse.WarehouseRequest;
import com.inventory.dto.response.product.ProductAddResponse;
import com.inventory.dto.response.warehouse.WarehouseAddResponse;
import com.inventory.service.ProductService;
import com.inventory.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.logging.Logger;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    private static final Logger logger = Logger.getLogger(WarehouseController.class.getName());
    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WarehouseAddResponse> addWarehouse(@Valid @NotNull @RequestBody WarehouseRequest request) {
        logger.info("Call the add warehouse api.");
        logger.info("The request data are: " + request);
        WarehouseAddResponse response = warehouseService.addWarehouse(request);
        logger.info("The response data are: " + response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}