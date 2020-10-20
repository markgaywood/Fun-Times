package com.pricing.domain;

import org.junit.jupiter.api.Test;

import static com.pricing.domain.Milk.milk;
import static com.pricing.domain.ProductUnit.Bottle;
import static org.assertj.core.api.Assertions.assertThat;

public class MilkTests implements InPence {
    @Test
    public void givenMilkIsAProduct_whenMadeAStockItem_thenItIsAStockItem() {
        StockItem actual = milk();

        assertThat(actual.getProduct().getName()).isEqualTo("Milk");
        assertThat(actual.getProduct().getProductUnit()).isEqualTo(Bottle);
        assertThat(actual.getProduct().getCost()).isEqualTo(pence(130));
    }
}
