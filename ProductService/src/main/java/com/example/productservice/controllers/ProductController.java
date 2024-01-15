package com.example.productservice.controllers;

import com.example.productservice.dtos.GenericProductDto;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<GenericProductDto> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.getProductById(id);

    }
    @PutMapping ("{id}")
    public GenericProductDto updateProductById(@RequestBody GenericProductDto genericProductDto, @PathVariable("id") Long id) throws NotFoundException {
        return productService.updateProductById(genericProductDto, id);
    }
    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto){
        return productService.createProduct(genericProductDto);
    }
}
