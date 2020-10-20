package com.pricing.stock;

import org.junit.jupiter.api.Test;

import static com.pricing.stock.Apples.apples;
import static com.pricing.product.Pence.pence;
import static com.pricing.product.ProductUnit.Single;
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
