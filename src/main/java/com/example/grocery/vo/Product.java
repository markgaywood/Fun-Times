package com.example.grocery.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private final String name;
    private final UnitOfMeasure unitOfMeasure;
    private final BigDecimal basePrice;
}
