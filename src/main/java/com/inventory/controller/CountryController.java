package com.inventory.controller;

import com.inventory.dto.request.category.CategoryRequest;
import com.inventory.dto.request.country.CountryRequest;
import com.inventory.dto.response.category.CategoryAddResponse;
import com.inventory.dto.response.country.CountryAddResponse;
import com.inventory.service.CategoryService;
import com.inventory.service.CountryService;
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
@RequestMapping("/country")
public class CountryController {
    private static final Logger logger = Logger.getLogger(CountryController.class.getName());
    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CountryAddResponse> addCountry(@Valid @NotNull @RequestBody CountryRequest request) {
        logger.info("Call the add country api.");
        logger.info("The request data are: " + request);
        CountryAddResponse response = countryService.addCountry(request);
        logger.info("The response data are: " + response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}