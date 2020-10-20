package com.pricing.stock;

import com.pricing.product.Product;
import lombok.Data;
import lombok.Getter;

@Data
public abstract class StockItem {
    @Getter
    private final Product product;

    protected StockItem(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return String.format("%s %s", product.getProductUnit().getLabel(),
            product.getName());
    }
}
