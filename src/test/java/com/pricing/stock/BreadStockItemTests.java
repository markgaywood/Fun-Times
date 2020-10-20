package com.pricing.stock;

import org.junit.jupiter.api.Test;

import static com.pricing.stock.Bread.bread;
import static com.pricing.product.Pence.pence;
import static com.pricing.product.ProductUnit.Loaf;
import static org.assertj.core.api.Assertions.assertThat;

public class BreadStockItemTests {
    @Test
    public void givenBreadIsAProduct_whenMadeAStockItem_thenItIsAStockItem() {
        StockItem actual = bread();

        assertThat(actual.getProduct().getName()).isEqualTo("Bread");
        assertThat(actual.getProduct().getProductUnit()).isEqualTo(Loaf);
        assertThat(actual.getProduct().getCost()).isEqualTo(pence(80));
    }
}
