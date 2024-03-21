package com.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GenericProductDto implements Serializable {
    private String id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}
