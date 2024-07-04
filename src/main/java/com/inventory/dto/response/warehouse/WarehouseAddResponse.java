package com.inventory.dto.response.warehouse;

import com.inventory.dto.response.success.SuccessResponse;
import com.inventory.model.db.warehouse.Warehouse;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.hc.core5.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class WarehouseAddResponse extends SuccessResponse {
    private final String id;
    private final String warehouseId;
    private final String warehouseName;
    private final String status;

    @Builder
    public WarehouseAddResponse(Integer code, String message, String id, String warehouseId, String warehouseName, String status) {
        super(code, message);
        this.id = id;
        this.warehouseId = warehouseId;
        this.warehouseName = warehouseName;
        this.status = status;
    }

    public static WarehouseAddResponse from(Warehouse warehouse) {
        return WarehouseAddResponse.builder()
                .code(HttpStatus.SC_OK)
                .message("Warehouse add success.")
                .id(warehouse.getId())
                .warehouseId(warehouse.getWarehouseId())
                .warehouseName(warehouse.getWarehouseName())
                .build();
    }
}