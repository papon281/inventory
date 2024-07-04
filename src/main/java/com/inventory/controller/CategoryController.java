package com.inventory.controller;

import com.inventory.dto.request.brand.BrandRequest;
import com.inventory.dto.request.category.CategoryRequest;
import com.inventory.dto.response.brand.BrandAddResponse;
import com.inventory.dto.response.category.CategoryAddResponse;
import com.inventory.service.BrandService;
import com.inventory.service.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {
    private static final Logger logger = Logger.getLogger(CategoryController.class.getName());
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CategoryAddResponse> addCategory(@Valid @NotNull @RequestBody CategoryRequest request) {
        logger.info("Call the add category api.");
        logger.info("The request data are: " + request);
        CategoryAddResponse response = categoryService.addCategory(request);
        logger.info("The response data are: " + response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}