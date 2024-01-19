package com.example.productservice.mappers;

import com.example.productservice.models.Product;
import com.example.productservice.thirdpartyclients.fakestore.dtos.FakeStoreApiProductDto;
import com.example.productservice.dtos.GenericProductDto;

import java.util.UUID;

public class DtoMapper {
    public static GenericProductDto fakeStoreToGenericProductDtoMapper(FakeStoreApiProductDto fakeStoreApiProductDto) {
        GenericProductDto genericProductDto = new GenericProductDto();
        if(fakeStoreApiProductDto.getId()!=null){
            genericProductDto.setId(String.valueOf(fakeStoreApiProductDto.getId()));
        }
        genericProductDto.setCategory(fakeStoreApiProductDto.getCategory());
        genericProductDto.setDescription(fakeStoreApiProductDto.getDescription());
        genericProductDto.setImage(fakeStoreApiProductDto.getImage());
        genericProductDto.setPrice(fakeStoreApiProductDto.getPrice());
        genericProductDto.setTitle(fakeStoreApiProductDto.getTitle());
        return genericProductDto;
    }
    public static FakeStoreApiProductDto genericToFakeStoreProductDtoMapper(GenericProductDto genericProductDto) {
        FakeStoreApiProductDto fakeStoreApiProductDto = new FakeStoreApiProductDto();
        if(genericProductDto.getId()!=null) {
            fakeStoreApiProductDto.setId(Long.valueOf(genericProductDto.getId()));
        }
        fakeStoreApiProductDto.setCategory(genericProductDto.getCategory());
        fakeStoreApiProductDto.setDescription(genericProductDto.getDescription());
        fakeStoreApiProductDto.setImage(genericProductDto.getImage());
        fakeStoreApiProductDto.setPrice(genericProductDto.getPrice());
        fakeStoreApiProductDto.setTitle(genericProductDto.getTitle());
        return fakeStoreApiProductDto;
    }
    public static Product genericProductToProductDtoMapper(GenericProductDto genericProductDto) {
        Product product = new Product();
        if(genericProductDto.getId()!=null) {
            product.setUuid(UUID.fromString(genericProductDto.getId()));
        }
        product.setDescription(genericProductDto.getDescription());
        product.setImage(genericProductDto.getImage());
        product.setPrice(genericProductDto.getPrice());
        product.setTitle(genericProductDto.getTitle());
        return product;
    }
    public static GenericProductDto productToGenericProductDtoMapper(Product product) {
        GenericProductDto genericProductDto = new GenericProductDto();
        if(product.getUuid()!=null) {
            genericProductDto.setId(product.getUuid().toString());
        }
        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setPrice(product.getPrice());
        genericProductDto.setTitle(product.getTitle());
        return genericProductDto;
    }
}
