package com.pricing.domain;

import org.junit.jupiter.api.Test;

import static com.pricing.domain.Apples.apples;
import static com.pricing.domain.Pence.pence;
import static com.pricing.domain.ProductUnit.Single;
import static org.assertj.core.api.Assertions.assertThat;

public class ApplesStockItemTests {
    @Test
    public void givenApplesIsAProduct_whenMadeAStockItem_thenItIsAStockItem() {
        StockItem actual = apples();

        assertThat(actual.getProduct().getName()).isEqualTo("Apples");
        assertThat(actual.getProduct().getProductUnit()).isEqualTo(Single);
        assertThat(actual.getProduct().getCost()).isEqualTo(pence(10));
    }
}
