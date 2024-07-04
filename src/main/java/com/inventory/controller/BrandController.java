package com.inventory.controller;

import com.inventory.dto.request.brand.BrandRequest;
import com.inventory.dto.response.brand.BrandAddResponse;
import com.inventory.service.BrandService;
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
@RequestMapping("/brand")
public class BrandController {
    private static final Logger logger = Logger.getLogger(BrandController.class.getName());
    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BrandAddResponse> addBrand(@Valid @NotNull @RequestBody BrandRequest request) {
        logger.info("Call the add brand api.");
        logger.info("The request data are: " + request);
        BrandAddResponse response = brandService.addBrand(request);
        logger.info("The response data are: " + response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}