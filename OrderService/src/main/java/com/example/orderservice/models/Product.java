package com.example.orderservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product extends BaseModel{
     @Column(columnDefinition = "binary(16)", nullable = false)
     private UUID productId;
     private int quantity;
     private double pricePerProduct;
     private double totalPrice;

     public Product(UUID productId, int quantity, double pricePerProduct) {
          this.productId = productId;
          this.quantity = quantity;
          this.pricePerProduct = pricePerProduct;
          this.totalPrice = quantity*pricePerProduct;
     }
}
