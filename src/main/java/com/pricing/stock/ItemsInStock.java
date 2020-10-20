package com.pricing.stock;

import java.util.*;

import static com.pricing.stock.Apples.apples;
import static com.pricing.stock.Bread.bread;
import static com.pricing.stock.Milk.milk;
import static com.pricing.stock.Soup.soup;
import static java.util.Collections.unmodifiableMap;
import static java.util.stream.Collectors.toMap;

public final class ItemsInStock implements StockItems {
    public static StockItems loadItems() {
        Set<StockItem> inStock = new HashSet<>();
        inStock.add(soup());
        inStock.add(bread());
        inStock.add(milk());
        inStock.add(apples());

        return loadItems(inStock);
    }

    private static StockItems loadItems(Set<StockItem> items) { return new ItemsInStock(items); }

    private Map<String, StockItem> itemsByName;

    private ItemsInStock(Set<StockItem> items) {
        itemsByName = unmodifiableMap(items.stream().collect(toMap(x -> x.getProduct().getName(), x -> x)));
    }

    @Override
    public Optional<StockItem> findByName(String value) {
        return Optional.ofNullable(itemsByName.get(value));
    }
}
