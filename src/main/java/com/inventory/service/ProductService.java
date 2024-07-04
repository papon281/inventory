package com.inventory.service;

import com.inventory.dto.request.product.ProductRequest;
import com.inventory.dto.response.product.ProductAddResponse;

public interface ProductService {
    ProductAddResponse addProduct(ProductRequest request);
}