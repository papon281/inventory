package com.inventory.dto.request.product;

import com.inventory.dto.RequestValidator;
import lombok.Data;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
@ToString
public class ProductRequest implements RequestValidator {
    @Valid
    @NotEmpty
    private String productId;

    @Valid
    @NotEmpty
    private String productName;

    @Valid
    @NotEmpty
    private String email;

    @Override
    public void validate() {

    }
}
