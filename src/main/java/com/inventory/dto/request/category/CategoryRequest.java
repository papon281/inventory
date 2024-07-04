package com.inventory.dto.request.category;

import com.inventory.dto.RequestValidator;
import lombok.Data;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
@ToString
public class CategoryRequest implements RequestValidator {
    @Valid
    @NotEmpty
    private String categoryId;

    @Valid
    @NotEmpty
    private String categoryName;

    @Valid
    @NotEmpty
    private String email;

    @Override
    public void validate() {

    }
}
