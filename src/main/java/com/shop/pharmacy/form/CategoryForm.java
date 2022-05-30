package com.shop.pharmacy.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryForm {
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;

    private Integer medicineCount;
}
