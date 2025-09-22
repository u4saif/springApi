package com.codewithsaif.store.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class UpdateCartRequest {
    @NotNull(message = "Quantity Can not be zero or less")
    @Min(value = 1, message = "Please provide quantity greater than zero")
    private Integer quantity;
}
