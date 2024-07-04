package com.inventory.dto.response.category;

import com.inventory.dto.response.success.SuccessResponse;
import com.inventory.model.db.category.Category;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.hc.core5.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class CategoryAddResponse extends SuccessResponse {
    private final String id;
    private final String categoryId;
    private final String categoryName;
    private final String status;

    @Builder
    public CategoryAddResponse(Integer code, String message, String id, String categoryId, String categoryName, String status) {
        super(code, message);
        this.id = id;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.status = status;
    }

    public static CategoryAddResponse from(Category category) {
        return CategoryAddResponse.builder()
                .code(HttpStatus.SC_OK)
                .message("Category add success.")
                .id(category.getId())
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .build();
    }
}