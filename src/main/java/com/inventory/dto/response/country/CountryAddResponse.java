package com.inventory.dto.response.country;

import com.inventory.dto.response.success.SuccessResponse;
import com.inventory.model.db.country.Country;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.hc.core5.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class CountryAddResponse extends SuccessResponse {
    private final String id;
    private final String countryId;
    private final String countryName;
    private final String status;

    @Builder
    public CountryAddResponse(Integer code, String message, String id, String countryId, String countryName, String status) {
        super(code, message);
        this.id = id;
        this.countryId = countryId;
        this.countryName = countryName;
        this.status = status;
    }

    public static CountryAddResponse from(Country country) {
        return CountryAddResponse.builder()
                .code(HttpStatus.SC_OK)
                .message("Country add success.")
                .id(country.getId())
                .countryId(country.getCountryId())
                .countryName(country.getCountryName())
                .build();
    }
}