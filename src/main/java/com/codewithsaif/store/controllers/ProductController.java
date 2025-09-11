package com.codewithsaif.store.controllers;

import com.codewithsaif.store.dtos.ProductDto;
import com.codewithsaif.store.entities.Product;
import com.codewithsaif.store.mappers.ProductMapper;
import com.codewithsaif.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @GetMapping
    private Iterable<ProductDto> getProducts(
            @RequestParam(name = "categoryId" , required = false) Byte categoryId
    ){
        List<Product> productlist;
        if(categoryId != null){
            productlist = productRepository.findByCategoryId(categoryId);
        } else {
            productlist = productRepository.findWithCategory();
        }
        return productlist.stream()
                .map(productMapper::toDto)
                .toList();
    }
}
