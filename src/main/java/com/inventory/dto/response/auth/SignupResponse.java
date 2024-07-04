package com.inventory.dto.response.auth;

import com.inventory.dto.response.success.SuccessResponse;
import com.inventory.model.db.user.User;
import com.inventory.util.DateUtil;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.hc.core5.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class SignupResponse extends SuccessResponse {
    private final String id;
    private final String name;
    private final String email;
    private final String password;
    private final Long mobileNumber;
    private final String dateOfBirth;
    private final String gender;
    private final String status;
    private final String createdOn;

    @Builder
    public SignupResponse(Integer code, String message, String id, String name, String email, String password,
                          Long mobileNumber, String dateOfBirth, String gender, String status, String createdOn) {
        super(code, message);
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.status = status;
        this.createdOn = createdOn;
    }

    public static SignupResponse from(User user) {
        return SignupResponse.builder()
                .code(HttpStatus.SC_OK)
                .message("Registration Success.")
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .mobileNumber(user.getMobileNumber())
                .dateOfBirth(DateUtil.getFormattedDate(user.getDateOfBirth()))
                .gender(String.valueOf(user.getGender()))
                .status(String.valueOf(user.getStatus()))
                .createdOn(DateUtil.getFormattedDate(user.getCreatedOn()))
                .build();
    }
}