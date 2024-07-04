package com.inventory.service.impl;

import com.inventory.constant.enums.Gender;
import com.inventory.constant.enums.Status;
import com.inventory.dto.request.user.SignInRequest;
import com.inventory.dto.request.user.SignupRequest;
import com.inventory.dto.response.auth.SignInResponse;
import com.inventory.dto.response.auth.SignupResponse;
import com.inventory.exception.ExistException;
import com.inventory.exception.NotFoundException;
import com.inventory.exception.NotMatchedException;
import com.inventory.model.db.user.User;
import com.inventory.repository.UserRepository;
import com.inventory.service.AuthService;
import com.inventory.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AuthServiceImpl implements AuthService {
    private static final Logger logger = Logger.getLogger(AuthServiceImpl.class.getName());
    private final UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public SignInResponse signIn(SignInRequest request) {
        User user = findUser(request.getEmail());
        if (!user.getPassword().equals(request.getPassword())) {
            throw new NotMatchedException("The given password is not matched with user password.");
        }
        return SignInResponse.from(user);
    }

    @Override
    public SignupResponse signUp(SignupRequest request) {
        request.validate();
        logger.info("Sign up request validate success.");
        checkDuplicate(request.getEmail());
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setDateOfBirth(DateUtil.getZoneDateTime(request.getDateOfBirth() + "T00:00:00"));
        user.setGender(Gender.fromName(request.getGender()));
        //user.setStatus(Status.ACTIVE);
        user.setMobileNumber(Long.valueOf(request.getMobileNumber()));
        userRepository.save(user);
        logger.info("Sign up data save success: " + user);
        return SignupResponse.from(user);
    }

    private void checkDuplicate(String email) {
        Optional<User> optionalUser = userRepository.findByEmailAndStatus(email, Status.ACTIVE);
        if (optionalUser.isPresent()) {
            throw new ExistException("The given email is already exist to another user: " + email);
        }
    }

    private User findUser(String email) {
        Optional<User> optionalUser = userRepository.findByEmailAndStatus(email, Status.ACTIVE);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("We don't find any user using this email: " + email);
        } else {
            return optionalUser.get();
        }
    }
}