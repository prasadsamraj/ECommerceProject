package com.example.productservice.services;

import com.example.productservice.dtos.GenericProductDto;
import com.example.productservice.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    GenericProductDto getProductById(Long id) throws NotFoundException;

    GenericProductDto createProduct(GenericProductDto genericProductDto);

    GenericProductDto updateProductById(GenericProductDto genericProductDto, Long id) throws NotFoundException;

    List<GenericProductDto> getAllProducts();
}
