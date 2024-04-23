package com.example.orderservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderResponseDto {
    private String orderId;
    private double orderPrice;
    private ResponseStatus responseStatus;
    private String responseMessage;
}
