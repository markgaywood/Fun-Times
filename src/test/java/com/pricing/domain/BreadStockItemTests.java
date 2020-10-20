package com.pricing.domain;

import org.junit.jupiter.api.Test;

import static com.pricing.domain.Bread.bread;
import static com.pricing.domain.Pence.pence;
import static com.pricing.domain.ProductUnit.Loaf;
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
