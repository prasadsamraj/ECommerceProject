package com.example.productservice.services;

import com.example.productservice.dtos.GenericProductDto;
import com.example.productservice.exceptions.InvalidCategoryException;
import com.example.productservice.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    GenericProductDto getProductById(String id) throws NotFoundException;

    GenericProductDto createProduct(GenericProductDto genericProductDto) throws InvalidCategoryException;

    GenericProductDto updateProductById(GenericProductDto genericProductDto, String id) throws NotFoundException, InvalidCategoryException;

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProductById(String id) throws NotFoundException;

    List<String> getAllCategories();

    List<GenericProductDto> getProductsByCategory(String category) throws InvalidCategoryException, NotFoundException;
}
