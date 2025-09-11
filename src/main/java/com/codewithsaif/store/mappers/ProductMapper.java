package com.codewithsaif.store.mappers;

import com.codewithsaif.store.dtos.ProductDto;
import com.codewithsaif.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "categoryId" , source = "category.id")
    ProductDto toDto(Product product);
}
