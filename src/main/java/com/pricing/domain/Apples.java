package com.pricing.domain;

import lombok.EqualsAndHashCode;
import lombok.Value;

import static com.pricing.domain.ProductUnit.Single;

@Value
@EqualsAndHashCode(callSuper = true)
public class Apples extends StockItem {
    public static final String NAME = "Apples";

    private static StockItem APPLES = new Apples();

    public static StockItem apples() { return APPLES; }

    private Apples() {
        super(Product.builder()
                .name(NAME)
                .productUnit(Single)
                .cost(Pence.builder()
                        .value(10).build())
                .build());
    }
}