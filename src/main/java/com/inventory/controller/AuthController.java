package com.inventory.controller;

import com.inventory.dto.request.user.SignInRequest;
import com.inventory.dto.request.user.SignupRequest;
import com.inventory.dto.response.auth.SignInResponse;
import com.inventory.dto.response.auth.SignupResponse;
import com.inventory.dto.response.error.ErrorResponse;
import com.inventory.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "User sign in", description = "Authenticate a user and return name and email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful sign in", content = @Content(schema =
            @Schema(implementation = SignInResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Input - Error in input value",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found - User email or password didn't matched",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
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

    @Operation(summary = "User sign up", description = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful registration", content = @Content(schema =
            @Schema(implementation = SignupResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input - Error in input value", content = @Content(schema =
            @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Conflict - User Already Exist", content = @Content(schema =
            @Schema(implementation = ErrorResponse.class)))
    })
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