package com.inventory.service;

import com.inventory.dto.request.user.SignInRequest;
import com.inventory.dto.request.user.SignupRequest;
import com.inventory.dto.response.auth.SignInResponse;
import com.inventory.dto.response.auth.SignupResponse;

public interface AuthService {
    SignInResponse signIn(SignInRequest request);

    SignupResponse signUp(SignupRequest request);
}