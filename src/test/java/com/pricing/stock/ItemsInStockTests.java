package com.pricing.stock;

import com.pricing.stock.ItemsInStock;
import com.pricing.stock.StockItem;
import com.pricing.stock.StockItems;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.pricing.stock.Apples.apples;
import static com.pricing.stock.Bread.bread;
import static com.pricing.stock.Milk.milk;
import static com.pricing.stock.Soup.soup;
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

    @Test
    public void givenApplesIsAStockItem_whenLookedUp_thenStockItemIsFound() {
        StockItem expectedItem = apples();

        assertThat(itemsInStock.findByName("Apples")).contains(expectedItem);
    }
}
