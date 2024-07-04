package com.inventory.dto.response.auth;

import com.inventory.dto.response.success.SuccessResponse;
import com.inventory.model.db.user.User;
import com.inventory.util.DateUtil;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.hc.core5.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class SignInResponse extends SuccessResponse {
    private final String name;
    private final String email;
    private final String loginTime;
    private final String createdTime;

    @Builder
    public SignInResponse(Integer code, String message, String name, String email, String loginTime, String createdTime) {
        super(code, message);
        this.name = name;
        this.email = email;
        this.loginTime = loginTime;
        this.createdTime = createdTime;
    }

    public static SignInResponse from(User user) {
        return SignInResponse.builder()
                .code(HttpStatus.SC_OK)
                .message("Login Success.")
                .name(user.getName())
                .email(user.getEmail())
                .loginTime(DateUtil.getFormattedDate(ZonedDateTime.now()))
                .createdTime(DateUtil.getFormattedTime(user.getCreatedOn()))
                .build();
    }
}