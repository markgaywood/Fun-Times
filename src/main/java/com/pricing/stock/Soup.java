package com.pricing.stock;

import com.pricing.product.Pence;
import com.pricing.product.Product;
import lombok.EqualsAndHashCode;
import lombok.Value;

import static com.pricing.product.ProductUnit.Tin;

@Value
@EqualsAndHashCode(callSuper = true)
public class Soup extends StockItem {
    public static final String NAME = "Soup";

    private static StockItem SOUP = new Soup();

    public static StockItem soup() { return SOUP; }

    private Soup() {
        super(Product.builder()
                .name(NAME)
                .productUnit(Tin)
                .cost(Pence.builder()
                        .value(65).build())
                .build());
    }
}
