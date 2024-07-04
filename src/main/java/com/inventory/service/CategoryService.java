package com.inventory.service;

import com.inventory.dto.request.category.CategoryRequest;
import com.inventory.dto.response.category.CategoryAddResponse;

public interface CategoryService {
    CategoryAddResponse addCategory(CategoryRequest request);
}