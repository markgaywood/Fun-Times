package com.pricing.stock;

import com.pricing.ordering.OrderedItem;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

import static java.util.Collections.unmodifiableSet;

@Value
public class Basket {
    @Builder
    public static Basket basket(Set<OrderedItem> orderedItems) { return new Basket(orderedItems); }

    private Basket(Set<OrderedItem> orderedItems) {
        this.orderedItems = unmodifiableSet(orderedItems);
    }

    Set<OrderedItem> orderedItems;
}
