package com.pricing.stock;

import com.pricing.product.Pence;
import com.pricing.product.Product;
import lombok.EqualsAndHashCode;
import lombok.Value;

import static com.pricing.product.ProductUnit.Bottle;

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
