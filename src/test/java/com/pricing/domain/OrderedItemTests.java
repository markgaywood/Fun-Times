package com.pricing.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static com.pricing.domain.OrderedItem.orderedItem;
import static com.pricing.domain.Pence.pence;
import static com.pricing.domain.Quantity.quantity;
import static com.pricing.domain.Soup.soup;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderedItemTests {
    @Test
    public void givenAStockItemAndQuantity_whenOrderedItemConstructed_thenOrderedItemCostIsCalculated() {
        StockItem stockItem = soup();
        Optional<OrderedItem> actual = orderedItem(stockItem, 3);

        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get().getQuantity()).isEqualTo(quantity(3).get());
        assertThat(actual.get().getWhen().getNow().toLocalDate()).isEqualTo(LocalDate.now());
        assertThat(actual.get().getCost()).isEqualTo(pence(3 * 65));
    }
}
