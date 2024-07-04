package com.inventory.service;

import com.inventory.dto.request.brand.BrandRequest;
import com.inventory.dto.response.brand.BrandAddResponse;

public interface BrandService {
    BrandAddResponse addBrand(BrandRequest request);
}