package com.example.orderservice.dtos;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenericProductDto {
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}
