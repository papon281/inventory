package com.inventory.controller;

import com.inventory.dto.request.country.CountryRequest;
import com.inventory.dto.request.product.ProductRequest;
import com.inventory.dto.response.country.CountryAddResponse;
import com.inventory.dto.response.product.ProductAddResponse;
import com.inventory.service.CountryService;
import com.inventory.service.ProductService;
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
@RequestMapping("/product")
public class ProductController {
    private static final Logger logger = Logger.getLogger(ProductController.class.getName());
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ProductAddResponse> addProduct(@Valid @NotNull @RequestBody ProductRequest request) {
        logger.info("Call the add product api.");
        logger.info("The request data are: " + request);
        ProductAddResponse response = productService.addProduct(request);
        logger.info("The response data are: " + response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}