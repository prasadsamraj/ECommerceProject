package com.example.orderservice.services;

import com.example.orderservice.dtos.GenericProductDto;
import com.example.orderservice.exceptions.InvalidOrderIdException;
import com.example.orderservice.exceptions.ProductIdInvalidException;
import com.example.orderservice.models.*;
import com.example.orderservice.repositories.AddressRepository;
import com.example.orderservice.repositories.OrderRepository;
import com.example.orderservice.repositories.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private AddressRepository addressRepository;
    private RestTemplate restTemplate;
    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, AddressRepository addressRepository, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.addressRepository = addressRepository;
        this.restTemplate = restTemplate;
    }
    public Order createOrder(String userId, Address address, List<ProductPair> productPairs, ModeOfPayment modeOfPayment) throws ProductIdInvalidException {
        UUID userUuid = UUID.fromString(userId);
        List<Product> products = new ArrayList<>();
        double totalOrderPrice = 0;
        for(ProductPair productPair:productPairs){
            UUID productUuid = UUID.fromString(productPair.getProductId());
            ResponseEntity<GenericProductDto> responseEntity = restTemplate.getForEntity(
                    "http://localhost:8011/products/{id}",
                    GenericProductDto.class,
                    productPair.getProductId());
            if(responseEntity.getStatusCode().is4xxClientError()){
                throw new ProductIdInvalidException();
            }
            double price = responseEntity.getBody().getPrice();
            Product product = new Product(productUuid, productPair.getQuantity(), price);
            productRepository.save(product);
            products.add(product);
            totalOrderPrice+=product.getTotalPrice();
        }
        addressRepository.save(address);
        Order order = new Order();
        order.setOrderPrice(totalOrderPrice);
        order.setOrderStatus(OrderStatus.PLACED);
        order.setProducts(products);
        order.setUserId(userUuid);
        order.setDeliveryAddress(address);
        order.setPaymentStatus(PaymentStatus.YET_TO_PAY);
        order.setModeOfPayment(modeOfPayment);
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders(String userId) {
        return orderRepository.findAllByUserId(UUID.fromString(userId));
    }

    public void updateOrderPayment(String orderId) throws InvalidOrderIdException {
        Optional<Order> optionalOrder = orderRepository.findById(UUID.fromString(orderId));
        if(optionalOrder.isEmpty()) throw new InvalidOrderIdException();
        Order order = optionalOrder.get();
        order.setPaymentStatus(PaymentStatus.PAID);
        orderRepository.save(order);
    }
}
