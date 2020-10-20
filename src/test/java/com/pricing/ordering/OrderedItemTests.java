package com.pricing.ordering;

import com.pricing.stock.StockItem;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static com.pricing.ordering.OrderedItem.orderedItem;
import static com.pricing.product.Pence.pence;
import static com.pricing.ordering.Quantity.quantity;
import static com.pricing.stock.Soup.soup;
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
