package com.inventory.dto.request.user;

import com.inventory.constant.Regex;
import com.inventory.dto.RequestValidator;
import com.inventory.exception.RequestValidationException;
import lombok.Data;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.regex.Pattern;

@Data
@ToString
public class SignupRequest implements RequestValidator {
    private static final Logger logger = Logger.getLogger(SignupRequest.class.getName());
    @Valid
    @NotEmpty
    private String name;
    @Valid
    @NotEmpty
    private String email;
    @Valid
    @NotEmpty
    private String password;
    @Valid
    @NotEmpty
    private String mobileNumber;
    @Valid
    @NotEmpty
    private String dateOfBirth;
    @Valid
    @NotEmpty
    private String gender;

    @Override
    public void validate() {
        logger.info("Validate signup request");
        if (!Pattern.matches(String.valueOf(Regex.EMAIL_PATTERN), email)) {
            throw new RequestValidationException("Email Pattern not matched. Valid Email : example@gmail.com");
        }

        if (!Regex.MOBILE_NUMBER_PATTERN.matcher(mobileNumber).matches()) {
            throw new RequestValidationException("Mobile Number Pattern not matched. Valid Mobile Number : " +
                    "01711223344");
        }

        if (!Pattern.matches(String.valueOf(Regex.PASSWORD_PATTERN), password)) {
            throw new RequestValidationException("Password Pattern not matched. Valid Password: " +
                    "Pass@12a");
        }

        if (Objects.isNull(gender)) {
            throw new RequestValidationException("Gender should be MALE or FEMALE");
        }
    }
}