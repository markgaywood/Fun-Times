package com.pricing.domain;

import lombok.Builder;
import lombok.Value;

import java.util.Optional;

import static com.pricing.domain.Quantity.quantity;
import static com.pricing.domain.Today.today;

@Value
@Builder
public class OrderedItem {
    public static Optional<OrderedItem> orderedItem(StockItem stockItem, Integer units) {
        return quantity(units).map(q -> OrderedItem.builder()
                .when(today())
                .stockItem(stockItem)
                .quantity(q).build());
    }
    public static Optional<OrderedItem> orderedItem(When when, StockItem stockItem, Integer units) {
        return quantity(units).map(q -> OrderedItem.builder()
                .when(when)
                .stockItem(stockItem)
                .quantity(q).build());
    }

    StockItem stockItem;
    Quantity quantity;
    When when;

    public Pence getCost() { return stockItem.getProduct().getCost().multiplyBy(quantity); }
}
