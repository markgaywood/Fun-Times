package com.pricing.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.pricing.domain.Soup.soup;
import static org.assertj.core.api.Assertions.assertThat;

public class ItemsInStockTests {
    private StockItems itemsInStock;

    @BeforeEach
    public void setUp() {
        itemsInStock = ItemsInStock.loadItems();
    }

    @Test
    public void givenSoupIsAStockItem_whenLookedUp_thenStockItemIsFound() {
        StockItem expectedItem = soup();

        assertThat(itemsInStock.findByName("Soup")).contains(expectedItem);
    }
}
