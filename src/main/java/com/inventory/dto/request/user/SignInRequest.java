package com.inventory.dto.request.user;

import com.inventory.dto.RequestValidator;
import lombok.Data;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
@ToString
public class SignInRequest implements RequestValidator {
    @Valid
    @NotEmpty
    private String email;
    @Valid
    @NotEmpty
    private String password;

    @Override
    public void validate() {

    }
}