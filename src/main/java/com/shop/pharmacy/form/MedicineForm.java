package com.shop.pharmacy.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class MedicineForm {

    private Long id;

    @NotNull(message = "You must Select any Category")
    private long categoryId;

    @NotBlank(message = "Please enter a name for the medicine")
    private String name;

    private int price;

    @Size(min = 10, message = "Invalid date")
    private String expiryDate;

}
