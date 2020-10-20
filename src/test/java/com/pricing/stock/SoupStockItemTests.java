package com.pricing.stock;

import org.junit.jupiter.api.Test;

import static com.pricing.product.Pence.pence;
import static com.pricing.product.ProductUnit.Tin;
import static com.pricing.stock.Soup.soup;
import static org.assertj.core.api.Assertions.assertThat;

public class SoupStockItemTests {
    @Test
    public void givenSoupIsAProduct_whenMadeAStockItem_thenItIsAStockItem() {
        StockItem actual = soup();

        assertThat(actual.getProduct().getName()).isEqualTo("Soup");
        assertThat(actual.getProduct().getProductUnit()).isEqualTo(Tin);
        assertThat(actual.getProduct().getCost()).isEqualTo(pence(65));
    }
}
