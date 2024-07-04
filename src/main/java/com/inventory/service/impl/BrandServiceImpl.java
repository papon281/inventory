package com.inventory.service.impl;

import com.inventory.constant.enums.Status;
import com.inventory.dto.request.brand.BrandRequest;
import com.inventory.dto.response.brand.BrandAddResponse;
import com.inventory.exception.ExistException;
import com.inventory.exception.NotFoundException;
import com.inventory.model.db.brand.Brand;
import com.inventory.model.db.user.User;
import com.inventory.repository.BrandRepository;
import com.inventory.repository.UserRepository;
import com.inventory.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class BrandServiceImpl implements BrandService {
    private static final Logger logger = Logger.getLogger(BrandServiceImpl.class.getName());
    private final BrandRepository brandRepository;
    private final UserRepository userRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, UserRepository userRepository) {
        this.brandRepository = brandRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BrandAddResponse addBrand(BrandRequest request) {
        checkDuplicateBrand(request.getBrandId(), request.getBrandName());
        Brand brand = new Brand();
        brand.setBrandId(request.getBrandId());
        brand.setBrandName(request.getBrandName());
        brand.setStatus(Status.ACTIVE);
        brand.setCreatedBy(getUser(request.getEmail()));
        brandRepository.save(brand);
        logger.info("Brand data save success: " + brand);
        return BrandAddResponse.from(brand);
    }

    private User getUser(String email) {
        Optional<User> optionalUser = userRepository.findByEmailAndStatus(email, Status.ACTIVE);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User not found: " + email);
        }
        return optionalUser.get();
    }

    private void checkDuplicateBrand(String brandId, String brandName) {
        Optional<Brand> duplicateById = brandRepository.findByBrandIdAndStatus(brandId, String.valueOf(Status.ACTIVE));
        Optional<Brand> duplicateByName = brandRepository.findByBrandNameAndStatus(brandName,
                String.valueOf(Status.ACTIVE));
        if (duplicateById.isPresent()) {
            throw new ExistException("Brand Id is exist: " + brandId);
        }
        if (duplicateByName.isPresent()) {
            throw new ExistException("Brand Name is exist: " + brandName);
        }
    }
}