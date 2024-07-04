package com.inventory.dto.request.warehouse;

import com.inventory.dto.RequestValidator;
import lombok.Data;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
@ToString
public class WarehouseRequest implements RequestValidator {
    @Valid
    @NotEmpty
    private String warehouseId;

    @Valid
    @NotEmpty
    private String warehouseName;

    @Valid
    @NotEmpty
    private String email;

    @Override
    public void validate() {

    }
}