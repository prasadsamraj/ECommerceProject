package com.example.userservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@Entity
public class Session extends BaseModel{
    private String token;
    @Enumerated(EnumType.ORDINAL)
    private SessionStatus sessionStatus;
    @ManyToOne
    private User user;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date expireAt;
}
