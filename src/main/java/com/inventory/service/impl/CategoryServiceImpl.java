package com.inventory.service.impl;

import com.inventory.constant.enums.Status;
import com.inventory.dto.request.category.CategoryRequest;
import com.inventory.dto.response.category.CategoryAddResponse;
import com.inventory.exception.ExistException;
import com.inventory.exception.NotFoundException;
import com.inventory.model.db.category.Category;
import com.inventory.model.db.user.User;
import com.inventory.repository.CategoryRepository;
import com.inventory.repository.UserRepository;
import com.inventory.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger logger = Logger.getLogger(CategoryServiceImpl.class.getName());
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CategoryAddResponse addCategory(CategoryRequest request) {
        checkDuplicateCategory(request.getCategoryId(), request.getCategoryName());
        Category category = new Category();
        category.setCategoryId(request.getCategoryId());
        category.setCategoryName(request.getCategoryName());
        category.setStatus(Status.ACTIVE);
        category.setCreatedBy(getUser(request.getEmail()));
        categoryRepository.save(category);
        logger.info("Category data save success: " + category);
        return CategoryAddResponse.from(category);
    }

    private void checkDuplicateCategory(String categoryId, String categoryName) {
        Optional<Category> duplicateById = categoryRepository.findByCategoryIdAndStatus(categoryId,
                String.valueOf(Status.ACTIVE));
        Optional<Category> duplicateByName = categoryRepository.findByCategoryNameAndStatus(categoryName,
                String.valueOf(Status.ACTIVE));
        if (duplicateById.isPresent()) {
            throw new ExistException("Category Id is exist: " + categoryId);
        }
        if (duplicateByName.isPresent()) {
            throw new ExistException("Category Name is exist: " + categoryName);
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