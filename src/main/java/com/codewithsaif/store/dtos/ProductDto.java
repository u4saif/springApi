package com.codewithsaif.store.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    public Long id;
    public String name;
    public BigDecimal price;
    public String description;
    public Byte categoryId;
}
