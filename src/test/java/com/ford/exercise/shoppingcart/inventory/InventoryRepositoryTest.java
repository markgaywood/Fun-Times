package com.ford.exercise.shoppingcart.inventory;

import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryRepositoryTest {

    @Test
    public void get() {

        InventoryRepository inventory = new InventoryRepository();

        StockItem soup = new StockItem("soup", "unit", 0.65);
        StockItem bread = new StockItem("bread", "loaf", 0.80);
        StockItem milk = new StockItem("milk", "bottle", 1.30);
        StockItem apples = new StockItem("apples", "single", 0.10);

        inventory.add(soup);
        inventory.add(bread);
        inventory.add(milk);
        inventory.add(apples);

        assertEquals(milk, inventory.get("milk"));
        assertEquals(null, inventory.get("doesnotexist"));

    }


}