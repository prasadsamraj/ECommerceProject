package com.example.orderservice.controllers;

import com.example.orderservice.dtos.CreateOrderRequestDto;
import com.example.orderservice.dtos.CreateOrderResponseDto;
import com.example.orderservice.dtos.ResponseStatus;
import com.example.orderservice.exceptions.InvalidOrderIdException;
import com.example.orderservice.exceptions.ProductIdInvalidException;
import com.example.orderservice.models.Order;
import com.example.orderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping()
    public CreateOrderResponseDto createOrder(@RequestBody CreateOrderRequestDto requestDto) throws ProductIdInvalidException {
        CreateOrderResponseDto responseDto = new CreateOrderResponseDto();
        Order order = orderService.createOrder(
               requestDto.getUserId(),
               requestDto.getAddress(),
               requestDto.getProductPairs(),
               requestDto.getModeOfPayment()
        );
        responseDto.setOrderId(order.getUuid().toString());
        responseDto.setOrderPrice(order.getOrderPrice());
        responseDto.setResponseMessage("Order Placed Successfully!");
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        return responseDto;
    }
    @GetMapping("{userId}")
    public List<Order> getAllOrders(@PathVariable("userId") String userId){
        return orderService.getAllOrders(userId);
    }
    @PutMapping("{orderId}")
    public ResponseEntity<String> updateOrderPayment(@PathVariable("orderId") String orderId) throws InvalidOrderIdException {
        orderService.updateOrderPayment(orderId);
        return new ResponseEntity<>("Order status is updated to Paid.", HttpStatus.OK);
    }
}
