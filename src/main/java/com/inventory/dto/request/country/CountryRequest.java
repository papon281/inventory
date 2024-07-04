package com.inventory.dto.request.country;

import com.inventory.dto.RequestValidator;
import lombok.Data;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
@ToString
public class CountryRequest implements RequestValidator {
    @Valid
    @NotEmpty
    private String countryId;

    @Valid
    @NotEmpty
    private String countryName;

    @Valid
    @NotEmpty
    private String email;

    @Override
    public void validate() {

    }
}
