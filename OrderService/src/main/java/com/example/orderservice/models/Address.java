package com.example.orderservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Address extends BaseModel{
    private String houseNumber;
    private String street;
    private String city;
    private String pinCode;
}
