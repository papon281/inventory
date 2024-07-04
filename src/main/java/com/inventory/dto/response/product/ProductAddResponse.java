package com.inventory.dto.response.product;

import com.inventory.dto.response.success.SuccessResponse;
import com.inventory.model.db.product.Product;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.hc.core5.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class ProductAddResponse extends SuccessResponse {
    private final String id;
    private final String productId;
    private final String productName;
    private final String status;

    @Builder
    public ProductAddResponse(Integer code, String message, String id, String productId, String productName, String status) {
        super(code, message);
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.status = status;
    }

    public static ProductAddResponse from(Product product) {
        return ProductAddResponse.builder()
                .code(HttpStatus.SC_OK)
                .message("Product add success.")
                .id(product.getId())
                .productId(product.getProductId())
                .productName(product.getProductName())
                .build();
    }
}