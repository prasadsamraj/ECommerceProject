package com.example.orderservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPair{
    private String productId;
    private int quantity;

    public ProductPair(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}