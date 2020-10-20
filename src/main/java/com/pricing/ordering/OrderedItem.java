package com.pricing.ordering;

import com.pricing.product.Pence;
import com.pricing.stock.StockItem;
import com.pricing.timing.When;
import lombok.Builder;
import lombok.Value;

import java.util.Optional;

import static com.pricing.ordering.Quantity.quantity;
import static com.pricing.timing.Today.today;

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
