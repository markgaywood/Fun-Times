package com.pricing.stock;

import com.pricing.product.Pence;
import com.pricing.product.Product;
import lombok.EqualsAndHashCode;
import lombok.Value;

import static com.pricing.product.ProductUnit.Loaf;

@Value
@EqualsAndHashCode(callSuper = true)
public class Bread extends StockItem {
    public static final String NAME = "Bread";

    private static StockItem BREAD = new Bread();

    public static StockItem bread() { return BREAD; }

    private Bread() {
        super(Product.builder()
                .name(NAME)
                .productUnit(Loaf)
                .cost(Pence.builder()
                        .value(80).build())
                .build());
    }
}
