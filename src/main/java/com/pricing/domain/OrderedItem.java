package com.pricing.domain;

import lombok.Builder;
import lombok.Value;

import java.util.Optional;

import static com.pricing.domain.Quantity.quantity;

@Value
@Builder
public class OrderedItem {
    public static Optional<OrderedItem> orderedItem(StockItem stockItem, Integer units) {
        return quantity(units).map(q -> OrderedItem.builder()
                .stockItem(stockItem)
                .quantity(q).build());
    }

    StockItem stockItem;
    Quantity quantity;

    public Pence getCost() { return stockItem.getProduct().getCost().multiplyBy(quantity); }
}
