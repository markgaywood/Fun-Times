package com.pricing.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.pricing.domain.Bread.bread;
import static com.pricing.domain.Milk.milk;
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

    @Test
    public void givenBreadIsAStockItem_whenLookedUp_thenStockItemIsFound() {
        StockItem expectedItem = bread();

        assertThat(itemsInStock.findByName("Bread")).contains(expectedItem);
    }

    @Test
    public void givenMilkIsAStockItem_whenLookedUp_thenStockItemIsFound() {
        StockItem expectedItem = milk();

        assertThat(itemsInStock.findByName("Milk")).contains(expectedItem);
    }
}
