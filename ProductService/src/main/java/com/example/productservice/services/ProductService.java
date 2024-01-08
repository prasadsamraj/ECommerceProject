package com.example.productservice.services;

import com.example.productservice.dtos.GenericProductDto;

import java.util.List;

public interface ProductService {
    GenericProductDto getProductById(Long id);

    GenericProductDto createProduct(GenericProductDto genericProductDto);

    GenericProductDto updateProductById(GenericProductDto genericProductDto, Long id);

    List<GenericProductDto> getAllProducts();
}
