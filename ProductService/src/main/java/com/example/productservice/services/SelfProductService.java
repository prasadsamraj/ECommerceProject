package com.example.productservice.services;

import com.example.productservice.dtos.GenericProductDto;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.mappers.DtoMapper;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("selfProductService")
public class SelfProductService implements ProductService {
    ProductRepository productRepository;
    @Autowired
    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public GenericProductDto getProductById(String id) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(id));
        if(optionalProduct.isEmpty()) throw new NotFoundException("The requested product with id: " + id + " doesn't exists");
        Product product = optionalProduct.get();
        return DtoMapper.productToGenericProductDtoMapper(product);
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        Product product = DtoMapper.genericProductToProductDtoMapper(genericProductDto);
        product = productRepository.save(product);
        return DtoMapper.productToGenericProductDtoMapper(product);
    }

    @Override
    public GenericProductDto updateProductById(GenericProductDto genericProductDto, String id) throws NotFoundException {
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDto deleteProductById(String id) throws NotFoundException {
        return null;
    }
}
