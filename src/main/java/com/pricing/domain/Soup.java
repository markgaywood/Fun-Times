package com.pricing.domain;

import lombok.EqualsAndHashCode;
import lombok.Value;

import static com.pricing.domain.ProductUnit.Tin;

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
