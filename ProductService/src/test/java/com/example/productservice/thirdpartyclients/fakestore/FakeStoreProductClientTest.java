package com.example.productservice.thirdpartyclients.fakestore;

import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.thirdpartyclients.fakestore.dtos.FakeStoreApiProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class FakeStoreProductClientTest {
    private FakeStoreProductClient fakeStoreProductClient;
    private RestTemplateBuilder restTemplateBuilder;
    private RestTemplate restTemplate;

    public FakeStoreProductClientTest() {
        this.restTemplateBuilder = Mockito.mock(RestTemplateBuilder.class);
        this.restTemplate = Mockito.mock(RestTemplate.class);
        this.fakeStoreProductClient = new FakeStoreProductClient(restTemplateBuilder,null, null, null);
    }
    @Test
    public void testGetProductByIdIsNull(){
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
        ResponseEntity<FakeStoreApiProductDto> response = new ResponseEntity<>(null,HttpStatus.OK);
        when(restTemplate.getForEntity(any(),
                any(),
                any(String.class)))
                .thenReturn(new ResponseEntity<>(response, HttpStatus.OK));
        Assertions.assertThrows(NotFoundException.class, ()->{
            fakeStoreProductClient.getProductById("1");
        });
    }
    @Test
    public void testGetProductById() throws NotFoundException {
        when(restTemplateBuilder.build())
                .thenReturn(restTemplate);

        ResponseEntity<FakeStoreApiProductDto> responseMock =
                new ResponseEntity<>(null, HttpStatus.OK);

        when(restTemplate.getForEntity(any(String.class),
                        eq(FakeStoreApiProductDto.class),
                        eq("1L")))
                .thenReturn(responseMock);

        FakeStoreApiProductDto response = fakeStoreProductClient.getProductById("1L");

        Assertions.assertNull(response);

    }
}
