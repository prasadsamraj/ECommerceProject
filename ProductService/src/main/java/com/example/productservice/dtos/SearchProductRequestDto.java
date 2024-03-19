package com.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchProductRequestDto {
    private String text;
    private int pageSize;
    private int pageNumber;
    private List<SortFilter> sortFilters;
}
