package com.example.grocery.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BasketItem {
    private final Product product;
    private int quantity;
}
