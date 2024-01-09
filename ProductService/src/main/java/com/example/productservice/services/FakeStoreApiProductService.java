package com.example.productservice.services;

import com.example.productservice.dtos.FakeStoreApiProductDto;
import com.example.productservice.dtos.GenericProductDto;
import com.example.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreApiProductService implements ProductService{
    String getProductByIdUrl = "https://fakestoreapi.com/products/{id}";
    String updateProductByIdUrl = "https://fakestoreapi.com/products/{id}";
    String createProductUrl = "https://fakestoreapi.com/products/";
    String getAllProductsUrl = "https://fakestoreapi.com/products/";
    RestTemplateBuilder restTemplateBuilder;
    @Autowired
    public FakeStoreApiProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate =  restTemplateBuilder.build();
        ResponseEntity<FakeStoreApiProductDto> response = restTemplate.getForEntity(getProductByIdUrl,
                FakeStoreApiProductDto.class, id);
        if(!response.hasBody()) throw new NotFoundException("The id: " + id + " id not found");
        return dtoMapper(response.getBody());
    }

    private GenericProductDto dtoMapper(FakeStoreApiProductDto fakeStoreApiProductDto) {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(fakeStoreApiProductDto.getId());
        genericProductDto.setCategory(fakeStoreApiProductDto.getCategory());
        genericProductDto.setDescription(fakeStoreApiProductDto.getDescription());
        genericProductDto.setImage(fakeStoreApiProductDto.getImage());
        genericProductDto.setPrice(fakeStoreApiProductDto.getPrice());
        genericProductDto.setTitle(fakeStoreApiProductDto.getTitle());
        return genericProductDto;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreApiProductDto> response = restTemplate.
                postForEntity(createProductUrl, genericProductDto, FakeStoreApiProductDto.class);
        return dtoMapper(response.getBody());
    }

    @Override
    public GenericProductDto updateProductById(GenericProductDto genericProductDto, Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreApiProductDto> response =
                restTemplate.exchange(
                        updateProductByIdUrl,
                        HttpMethod.PUT,
                        new HttpEntity<>(genericProductDto),
                        FakeStoreApiProductDto.class,
                        id
                );
        return dtoMapper(response.getBody());
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<List<FakeStoreApiProductDto>> response = restTemplate.exchange(
                getAllProductsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        List<GenericProductDto> genericProductDtos = response.getBody().stream().map(this::dtoMapper).toList();
        return genericProductDtos;
    }
}
