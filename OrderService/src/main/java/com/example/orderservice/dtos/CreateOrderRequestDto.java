package com.example.orderservice.dtos;

import com.example.orderservice.models.Address;
import com.example.orderservice.models.ModeOfPayment;
import com.example.orderservice.models.ProductPair;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateOrderRequestDto {
    private String userId;
    private List<ProductPair> productPairs;
    private Address address;
    private ModeOfPayment modeOfPayment;
}

