package com.pricing.domain;

import org.junit.jupiter.api.Test;

import static com.pricing.domain.ProductUnit.Bottle;
import static com.pricing.domain.ProductUnit.Tin;
import static com.pricing.domain.Soup.soup;
import static org.assertj.core.api.Assertions.assertThat;

public class SoupStockItemTests implements InPence {
    @Test
    public void givenSoupIsAProduct_whenMadeAStockItem_thenItIsAStockItem() {
        StockItem actual = soup();

        assertThat(actual.getProduct().getName()).isEqualTo("Soup");
        assertThat(actual.getProduct().getProductUnit()).isEqualTo(Tin);
        assertThat(actual.getProduct().getCost()).isEqualTo(pence(65));
    }
}
