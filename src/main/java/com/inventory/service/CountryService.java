package com.inventory.service;

import com.inventory.dto.request.country.CountryRequest;
import com.inventory.dto.response.country.CountryAddResponse;

public interface CountryService {
    CountryAddResponse addCountry(CountryRequest request);
}