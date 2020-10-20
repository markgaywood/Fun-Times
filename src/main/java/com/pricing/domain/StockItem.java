package com.pricing.domain;

import lombok.Data;
import lombok.Getter;

@Data
public abstract class StockItem {
    @Getter
    private final Product product;

    protected StockItem(Product product) {
        this.product = product;
    }
}
