package com.example.productservice.mappers;

import com.example.productservice.thirdpartyclients.fakestore.dtos.FakeStoreApiProductDto;
import com.example.productservice.dtos.GenericProductDto;

public class DtoMapper {
    public static GenericProductDto fakeStoreToGenericProductDtoMapper(FakeStoreApiProductDto fakeStoreApiProductDto) {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(fakeStoreApiProductDto.getId());
        genericProductDto.setCategory(fakeStoreApiProductDto.getCategory());
        genericProductDto.setDescription(fakeStoreApiProductDto.getDescription());
        genericProductDto.setImage(fakeStoreApiProductDto.getImage());
        genericProductDto.setPrice(fakeStoreApiProductDto.getPrice());
        genericProductDto.setTitle(fakeStoreApiProductDto.getTitle());
        return genericProductDto;
    }
    public static FakeStoreApiProductDto genericToFakeStoreProductDtoMapper(GenericProductDto genericProductDto) {
        FakeStoreApiProductDto fakeStoreApiProductDto = new FakeStoreApiProductDto();
        fakeStoreApiProductDto.setId(genericProductDto.getId());
        fakeStoreApiProductDto.setCategory(genericProductDto.getCategory());
        fakeStoreApiProductDto.setDescription(genericProductDto.getDescription());
        fakeStoreApiProductDto.setImage(genericProductDto.getImage());
        fakeStoreApiProductDto.setPrice(genericProductDto.getPrice());
        fakeStoreApiProductDto.setTitle(genericProductDto.getTitle());
        return fakeStoreApiProductDto;
    }
}
