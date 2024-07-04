package com.inventory.dto.request.brand;

import com.inventory.dto.RequestValidator;
import lombok.Data;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
@ToString
public class BrandRequest implements RequestValidator {
    @Valid
    @NotEmpty
    private String brandId;

    @Valid
    @NotEmpty
    private String brandName;

    @Valid
    @NotEmpty
    private String email;

    @Override
    public void validate() {

    }
}
