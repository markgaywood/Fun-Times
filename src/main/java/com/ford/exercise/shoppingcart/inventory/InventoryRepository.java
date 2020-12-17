package com.ford.exercise.shoppingcart.inventory;

import java.util.ArrayList;
import java.util.List;

public class InventoryRepository {

    private List<StockItem> stockItems;

    public InventoryRepository() {
        stockItems = new ArrayList<StockItem>();
    }

    public List<StockItem> getStockItems() {
        return stockItems;
    }

    public void setStockItems(List<StockItem> stockItems) {
        this.stockItems = stockItems;
    }

    public void add(StockItem item) {
        this.stockItems.add(item);
    }

    public StockItem get(String key) {
        return stockItems.stream()
                .filter(stockItem -> key.equals(stockItem.getProduct()))
                .findAny()
                .orElse(null);
    }
}
