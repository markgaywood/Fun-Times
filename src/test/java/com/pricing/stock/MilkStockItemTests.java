package com.pricing.stock;

import org.junit.jupiter.api.Test;

import static com.pricing.stock.Milk.milk;
import static com.pricing.product.Pence.pence;
import static com.pricing.product.ProductUnit.Bottle;
import static org.assertj.core.api.Assertions.assertThat;

public class MilkStockItemTests {
    @Test
    public void givenMilkIsAProduct_whenMadeAStockItem_thenItIsAStockItem() {
        StockItem actual = milk();

        assertThat(actual.getProduct().getName()).isEqualTo("Milk");
        assertThat(actual.getProduct().getProductUnit()).isEqualTo(Bottle);
        assertThat(actual.getProduct().getCost()).isEqualTo(pence(130));
    }
}
