package com.inventory.dto.response.brand;

import com.inventory.dto.response.success.SuccessResponse;
import com.inventory.model.db.brand.Brand;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.hc.core5.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class BrandAddResponse extends SuccessResponse {
    private final String id;
    private final String brandId;
    private final String brandName;
    private final String status;

    @Builder
    public BrandAddResponse(Integer code, String message, String id, String brandId, String brandName, String status){
        super(code, message);
        this.id = id;
        this.brandId = brandId;
        this.brandName = brandName;
        this.status = status;
    }

    public static BrandAddResponse from(Brand brand){
        return BrandAddResponse.builder()
                .code(HttpStatus.SC_OK)
                .message("Brand add success.")
                .id(brand.getId())
                .brandId(brand.getBrandId())
                .brandName(brand.getBrandName())
                .build();
    }
}