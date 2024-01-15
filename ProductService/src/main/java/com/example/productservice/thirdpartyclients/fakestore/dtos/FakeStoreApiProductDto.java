package com.example.productservice.thirdpartyclients.fakestore.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreApiProductDto {
    private Long id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}
