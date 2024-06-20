package com.inventory.controller;

import com.inventory.dto.request.user.SignInRequest;
import com.inventory.dto.request.user.SignupRequest;
import com.inventory.dto.response.auth.SignInResponse;
import com.inventory.dto.response.auth.SignupResponse;
import com.inventory.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.logging.Logger;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = Logger.getLogger(AuthController.class.getName());
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(
            value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity<SignInResponse> signIn(@Valid @NotNull @RequestBody SignInRequest request) {
        logger.info("Call the sign in service.");
        logger.info("The request data are: " + request);
        SignInResponse response = authService.signIn(request);
        logger.info("The response data are: " + response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(
            value = "/registration",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity<SignupResponse> signUp(@Valid @NotNull @RequestBody SignupRequest request) {
        logger.info("Call the sign up service.");
        logger.info("The request data are: " + request);
        SignupResponse response = authService.signUp(request);
        logger.info("The response data are: " + response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
