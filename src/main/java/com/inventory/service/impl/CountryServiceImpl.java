package com.inventory.service.impl;

import com.inventory.constant.enums.Status;
import com.inventory.dto.request.country.CountryRequest;
import com.inventory.dto.response.country.CountryAddResponse;
import com.inventory.exception.ExistException;
import com.inventory.exception.NotFoundException;
import com.inventory.model.db.country.Country;
import com.inventory.model.db.user.User;
import com.inventory.repository.CountryRepository;
import com.inventory.repository.UserRepository;
import com.inventory.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CountryServiceImpl implements CountryService {
    private static final Logger logger = Logger.getLogger(CountryServiceImpl.class.getName());
    private final CountryRepository countryRepository;
    private final UserRepository userRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, UserRepository userRepository) {
        this.countryRepository = countryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CountryAddResponse addCountry(CountryRequest request) {
        checkDuplicateCountry(request.getCountryId(), request.getCountryName());
        Country country = new Country();
        country.setCountryId(request.getCountryId());
        country.setCountryName(request.getCountryName());
        country.setStatus(Status.ACTIVE);
        country.setCreatedBy(getUser(request.getEmail()));
        countryRepository.save(country);
        logger.info("Country data save success: " + country);
        return CountryAddResponse.from(country);
    }

    private void checkDuplicateCountry(String countryId, String countryName) {
        Optional<Country> duplicateById = countryRepository.findByCountryIdAndStatus(countryId,
                String.valueOf(Status.ACTIVE));
        Optional<Country> duplicateByName = countryRepository.findByCountryNameAndStatus(countryName,
                String.valueOf(Status.ACTIVE));
        if (duplicateById.isPresent()) {
            throw new ExistException("Country Id is exist: " + countryId);
        }
        if (duplicateByName.isPresent()) {
            throw new ExistException("Country Name is exist: " + countryName);
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