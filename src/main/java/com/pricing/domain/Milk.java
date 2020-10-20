package com.pricing.domain;

import lombok.EqualsAndHashCode;
import lombok.Value;

import static com.pricing.domain.ProductUnit.Bottle;
import static com.pricing.domain.ProductUnit.Loaf;

@Value
@EqualsAndHashCode(callSuper = true)
public class Milk extends StockItem {
    public static final String NAME = "Milk";

    private static StockItem MILK = new Milk();

    public static StockItem milk() { return MILK; }

    private Milk() {
        super(Product.builder()
                .name(NAME)
                .productUnit(Bottle)
                .cost(Pence.builder()
                        .value(130).build())
                .build());
    }
}
