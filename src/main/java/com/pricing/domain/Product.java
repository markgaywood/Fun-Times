package com.pricing.domain;

import lombok.*;

@Builder
@Value
public class Product {
    String name;
    ProductUnit productUnit;
    Pence cost;
}
