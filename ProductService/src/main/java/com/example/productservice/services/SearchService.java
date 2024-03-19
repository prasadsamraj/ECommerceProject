package com.example.productservice.services;

import com.example.productservice.clients.KafkaProducerClient;
import com.example.productservice.dtos.GenericProductDto;
import com.example.productservice.dtos.SortFilter;
import com.example.productservice.dtos.SortOrderBy;
import com.example.productservice.mappers.DtoMapper;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private final ProductRepository productRepository;
    private KafkaProducerClient kafkaProducerClient;
    @Autowired
    public SearchService(ProductRepository productRepository, KafkaProducerClient kafkaProducerClient) {
        this.productRepository = productRepository;
        this.kafkaProducerClient = kafkaProducerClient;
    }

    public Page<GenericProductDto> searchProducts(String text, int pageNumber, int pageSize, List<SortFilter> sortFilters) {
        kafkaProducerClient.sendMessage("sendEmail", "Somebody searching for product");
        List<Sort.Order> orderList = new ArrayList<>();
        for(SortFilter sortFilter:sortFilters){
            if(sortFilter.getSortOrderBy().equals(SortOrderBy.ASC))
                orderList.add(Sort.Order.asc(sortFilter.getFilterValue()));
            else
                orderList.add(Sort.Order.desc(sortFilter.getFilterValue()));
        }
        Sort sort = Sort.by(orderList);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> productPage = null;
        try {
            productPage = productRepository.findAllByTitleContaining(text, pageable);
        } catch (PropertyReferenceException propertyReferenceException) {
            //returning an empty page if filter is incorrect.
            return new PageImpl<>(new ArrayList<>(),
                    pageable,
                    0);
        }
        List<GenericProductDto> genericProductDtoList = productPage.get()
                .map(DtoMapper::productToGenericProductDtoMapper)
                .toList();
        return new PageImpl<>(genericProductDtoList,
                productPage.getPageable(),
                productPage.getTotalElements());
    }
}
