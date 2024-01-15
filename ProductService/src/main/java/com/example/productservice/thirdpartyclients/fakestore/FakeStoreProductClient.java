package com.example.productservice.thirdpartyclients.fakestore;

import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.thirdpartyclients.fakestore.dtos.FakeStoreApiProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Component
public class FakeStoreProductClient {
    String getProductByIdUrl;
    String updateProductByIdUrl;
    String createProductUrl;
    String getAllProductsUrl;
    RestTemplateBuilder restTemplateBuilder;
    @Autowired
    public FakeStoreProductClient(RestTemplateBuilder restTemplateBuilder,
                                  @Value("${fakestore.api.baseurl}") String fakeStoreBaseUrl,
                                  @Value("${fakestore.api.product}") String productEndpoint) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.createProductUrl = fakeStoreBaseUrl + productEndpoint;
        this.getAllProductsUrl = fakeStoreBaseUrl + productEndpoint;
        this.getProductByIdUrl = fakeStoreBaseUrl + productEndpoint + "/{id}";
        this.updateProductByIdUrl = fakeStoreBaseUrl + productEndpoint + "/{id}";
    }

    public FakeStoreApiProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate =  restTemplateBuilder.build();
        ResponseEntity<FakeStoreApiProductDto> response = restTemplate.getForEntity(getProductByIdUrl,
                FakeStoreApiProductDto.class, id);
        if(!response.hasBody()) throw new NotFoundException("The id: " + id + " id not found");
        return response.getBody();
    }

    public FakeStoreApiProductDto createProduct(FakeStoreApiProductDto fakeStoreApiProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreApiProductDto> response = restTemplate.
                postForEntity(createProductUrl, fakeStoreApiProductDto, FakeStoreApiProductDto.class);
        return response.getBody();
    }

    public FakeStoreApiProductDto updateProductById(FakeStoreApiProductDto fakeStoreApiProductDto, Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreApiProductDto> response =
                restTemplate.exchange(
                        updateProductByIdUrl,
                        HttpMethod.PUT,
                        new HttpEntity<>(fakeStoreApiProductDto),
                        FakeStoreApiProductDto.class,
                        id
                );
        if(!response.hasBody()) throw new NotFoundException("The id: " + id + " id not found");
        return response.getBody();
    }

    public List<FakeStoreApiProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<List<FakeStoreApiProductDto>> response = restTemplate.exchange(
                getAllProductsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }
}
