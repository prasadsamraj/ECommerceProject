package com.example.productservice.services;

import com.example.productservice.dtos.GenericProductDto;
import com.example.productservice.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    GenericProductDto getProductById(String id) throws NotFoundException;

    GenericProductDto createProduct(GenericProductDto genericProductDto);

    GenericProductDto updateProductById(GenericProductDto genericProductDto, String id) throws NotFoundException;

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProductById(String id) throws NotFoundException;
}
