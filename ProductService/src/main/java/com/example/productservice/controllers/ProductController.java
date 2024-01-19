package com.example.productservice.controllers;

import com.example.productservice.dtos.GenericProductDto;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;
    @Autowired
    public ProductController(@Qualifier("fakeStoreApiProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<GenericProductDto> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") String id) throws NotFoundException {
        return productService.getProductById(id);

    }
    @PutMapping ("{id}")
    public GenericProductDto updateProductById(@RequestBody GenericProductDto genericProductDto, @PathVariable("id") String id) throws NotFoundException {
        return productService.updateProductById(genericProductDto, id);
    }
    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto){
        return productService.createProduct(genericProductDto);
    }
    @DeleteMapping("{id}")
    public GenericProductDto deleteProductById(@PathVariable("id") String id) throws NotFoundException{
        return productService.deleteProductById(id);
    }
    @GetMapping("/categories")
    public List<String> getAllCategories(){
        return productService.getAllCategories();
    }
    @GetMapping("/category/{category}")
    public List<GenericProductDto> getProductsByCategory(@PathVariable("category") String category) throws NotFoundException{
        return productService.getProductsByCategory(category);
    }
}
