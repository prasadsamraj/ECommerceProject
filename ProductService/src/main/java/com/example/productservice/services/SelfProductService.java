package com.example.productservice.services;

import com.example.productservice.dtos.GenericProductDto;
import com.example.productservice.exceptions.InvalidCategoryException;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.mappers.DtoMapper;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("selfProductService")
public class SelfProductService implements ProductService {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    @Autowired
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public GenericProductDto getProductById(String id) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(id));
        if(optionalProduct.isEmpty()) throw new NotFoundException("The requested product with id: " + id + " doesn't exists");
        Product product = optionalProduct.get();
        return DtoMapper.productToGenericProductDtoMapper(product);
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) throws InvalidCategoryException {
        Product product = DtoMapper.genericProductToProductDtoMapper(genericProductDto);
        Optional<Category> optionalCategory = categoryRepository.findByName(genericProductDto.getCategory());
        if(optionalCategory.isEmpty()) throw new InvalidCategoryException("The category: " + genericProductDto.getCategory() + " doesn't exists.");
        Category category = optionalCategory.get();
        product.setCategory(category);
        productRepository.save(product);
        return DtoMapper.productToGenericProductDtoMapper(product);
    }

    @Override
    public GenericProductDto updateProductById(GenericProductDto genericProductDto, String id) throws NotFoundException, InvalidCategoryException {
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(id));
        if(optionalProduct.isEmpty()) throw new NotFoundException("The requested product with id: " + id + " doesn't exists");
        Product product = optionalProduct.get();
        Optional<Category> optionalCategory = categoryRepository.findByName(genericProductDto.getCategory());
        if(optionalCategory.isEmpty()) throw new InvalidCategoryException("The category: " + genericProductDto.getCategory() + " doesn't exists.");
        Category category = optionalCategory.get();
        product.setTitle(genericProductDto.getTitle());
        product.setPrice(genericProductDto.getPrice());
        product.setImage(genericProductDto.getImage());
        product.setDescription(genericProductDto.getDescription());
        product.setCategory(category);
        productRepository.save(product);
        return DtoMapper.productToGenericProductDtoMapper(product);
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(DtoMapper::productToGenericProductDtoMapper).toList();
    }

    @Override
    public GenericProductDto deleteProductById(String id) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(id));
        if(optionalProduct.isEmpty()) throw new NotFoundException("The requested product with id: " + id + " doesn't exists");
        productRepository.deleteById(UUID.fromString(id));
        return DtoMapper.productToGenericProductDtoMapper(optionalProduct.get());
    }

    @Override
    public List<String> getAllCategories() {
        return categoryRepository.findAll().stream().map(Category::getName).toList();
    }

    @Override
    public List<GenericProductDto> getProductsByCategory(String category) throws InvalidCategoryException {
        Optional<Category> optionalCategory = categoryRepository.findByName(category);
        if(optionalCategory.isEmpty()) throw new InvalidCategoryException("The category: " + category + " doesn't exists.");
        return optionalCategory.get().getProducts().stream().map(DtoMapper::productToGenericProductDtoMapper).toList();
    }
}
