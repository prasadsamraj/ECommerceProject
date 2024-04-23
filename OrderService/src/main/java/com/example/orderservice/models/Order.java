package com.example.orderservice.models;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order extends BaseModel {
    @Column(columnDefinition = "binary(16)", nullable = false)
    private UUID userId;
    @OneToOne
    private Address deliveryAddress;
    private double orderPrice;
    @Enumerated(EnumType.ORDINAL)
    private ModeOfPayment modeOfPayment;
    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;
    @OneToMany
    private List<Product> products;
}
