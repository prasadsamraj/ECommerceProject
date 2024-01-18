package com.example.productservice.services;

import com.example.productservice.mappers.DtoMapper;
import com.example.productservice.thirdpartyclients.fakestore.FakeStoreProductClient;
import com.example.productservice.dtos.GenericProductDto;
import com.example.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fakeStoreApiProductService")
public class FakeStoreApiProductService implements ProductService{
    FakeStoreProductClient fakeStoreProductClient;
    @Autowired
    public FakeStoreApiProductService(FakeStoreProductClient fakeStoreProductClient) {
        this.fakeStoreProductClient = fakeStoreProductClient;
    }

    @Override
    public GenericProductDto getProductById(String id) throws NotFoundException {
        return DtoMapper.fakeStoreToGenericProductDtoMapper(fakeStoreProductClient.getProductById(id));
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        return DtoMapper.fakeStoreToGenericProductDtoMapper(fakeStoreProductClient.createProduct(DtoMapper.genericToFakeStoreProductDtoMapper(genericProductDto)));
    }

    @Override
    public GenericProductDto updateProductById(GenericProductDto genericProductDto, String id) throws NotFoundException {
        return DtoMapper.fakeStoreToGenericProductDtoMapper(fakeStoreProductClient.updateProductById(DtoMapper.genericToFakeStoreProductDtoMapper(genericProductDto), id));
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return fakeStoreProductClient.getAllProducts().stream().map(DtoMapper::fakeStoreToGenericProductDtoMapper).toList();
    }

    @Override
    public GenericProductDto deleteProductById(String id) throws NotFoundException {
        return DtoMapper.fakeStoreToGenericProductDtoMapper(fakeStoreProductClient.deleteProductById(id));
    }
}
