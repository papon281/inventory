package com.inventory.service.impl;

import com.inventory.constant.enums.Status;
import com.inventory.dto.request.product.ProductRequest;
import com.inventory.dto.response.product.ProductAddResponse;
import com.inventory.exception.ExistException;
import com.inventory.exception.NotFoundException;
import com.inventory.model.db.product.Product;
import com.inventory.model.db.user.User;
import com.inventory.repository.ProductRepository;
import com.inventory.repository.UserRepository;
import com.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = Logger.getLogger(ProductServiceImpl.class.getName());
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ProductAddResponse addProduct(ProductRequest request) {
        checkDuplicateProduct(request.getProductId(), request.getProductName());
        Product product = new Product();
        product.setProductId(request.getProductId());
        product.setProductName(request.getProductName());
        product.setStatus(Status.ACTIVE);
        product.setCreatedBy(getUser(request.getEmail()));
        productRepository.save(product);
        logger.info("Product data save success: " + product);
        return ProductAddResponse.from(product);
    }

    private void checkDuplicateProduct(String productId, String productName) {
        Optional<Product> duplicateById = productRepository.findByProductIdAndStatus(productId,
                String.valueOf(Status.ACTIVE));
        Optional<Product> duplicateByName = productRepository.findByProductNameAndStatus(productName,
                String.valueOf(Status.ACTIVE));
        if (duplicateById.isPresent()) {
            throw new ExistException("Product Id is exist: " + productId);
        }
        if (duplicateByName.isPresent()) {
            throw new ExistException("Product Name is exist: " + productName);
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