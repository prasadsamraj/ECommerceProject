package com.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortFilter {
    private String filterValue;
    private SortOrderBy sortOrderBy;
}
