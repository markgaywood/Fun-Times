package funtimes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    @Test
    @DisplayName("Counting number of stock items in a shopping cart")
    void testNumberOf() {
        ShoppingCart cart = new ShoppingCart();
        cart.add(StockItem.APPLE);
        cart.add(StockItem.APPLE);
        assertEquals(2, cart.unitsOf(StockItem.APPLE));
    }

    @Test
    @DisplayName("Calculate running totals before discount is applied")
    void testRunningTotals() {
        ShoppingCart cart = new ShoppingCart();
        cart.add(StockItem.APPLE);
        cart.add(StockItem.APPLE);
        cart.add(StockItem.SOUP);
        assertEquals(DecimalHelper
                .bigDecimalFromDoubleValue(StockItem.APPLE.getCost().doubleValue()
                                           * 2
                                           + StockItem.SOUP.getCost().doubleValue()), cart.calculateRunningTotalBeforeDiscount());
    }

    @Test
    @DisplayName("Calculate discounts when discount is not available")
    void calculateDiscountsNotAvailable() {
        ShoppingCart cart = new ShoppingCart();
        cart.add(StockItem.MILK);
        cart.add(StockItem.SOUP);
        assertEquals(Collections.emptyMap(), cart.calculateDiscounts());
    }

    @Test
    @DisplayName("Calculate discounts on apples, bought today")
    void calculateDiscountsOnAppleBoughtToday() {
        ShoppingCart cart = new ShoppingCart();
        cart.add(StockItem.MILK);
        cart.add(StockItem.SOUP);
        cart.add(StockItem.APPLE);
        cart.add(StockItem.APPLE);
        assertEquals(Collections.emptyMap(), cart.calculateDiscounts());
    }

    @Test
    @DisplayName("Calculate discounts on apples, bought in 5 days time")
    void calculateDiscountsOnApple() {
        ShoppingCart cart = new ShoppingCart(5);
        cart.add(StockItem.MILK);
        cart.add(StockItem.SOUP);
        cart.add(StockItem.APPLE);
        cart.add(StockItem.APPLE);
        Map<StockItem, BigDecimal> discounts = cart.calculateDiscounts();
        assertAll(
                () -> assertEquals(1, discounts.keySet().size()),
                () -> assertEquals(DecimalHelper.bigDecimalFromDoubleValue(0.02), discounts.get(StockItem.APPLE))
        );
    }

    @Test
    @DisplayName("Calculate discounts on bread, bought today")
    void calculateDiscountsOnBread() {
        ShoppingCart cart = new ShoppingCart();
        cart.add(StockItem.BREAD);
        cart.add(StockItem.SOUP, 3);
        Map<StockItem, BigDecimal> discounts = cart.calculateDiscounts();
        assertAll(
                () -> assertEquals(1, discounts.keySet().size()),
                () -> assertEquals(DecimalHelper.bigDecimalFromDoubleValue(0.40), discounts.get(StockItem.BREAD)));
    }

    @Test
    @DisplayName("Fun times Grocery Scenario 1 [3 tins of soup and 2 loaves of bread, bought today]")
    void scenario1() {
        ShoppingCart cart = new ShoppingCart();
        cart.add(StockItem.SOUP, 3);
        cart.add(StockItem.BREAD, 2);
        assertEquals(BigDecimal.valueOf(3.15), cart.calculateTotalAfterDiscount());
    }

    @Test
    @DisplayName("Fun times Grocery Scenario 2 [6 apples and a bottle of milk, bought today]")
    void scenario2() {
        ShoppingCart cart = new ShoppingCart();
        cart.add(StockItem.APPLE, 6);
        cart.add(StockItem.MILK);
        assertEquals(DecimalHelper.bigDecimalFromDoubleValue(1.90), cart.calculateTotalAfterDiscount());
    }

    @Test
    @DisplayName("Fun times Grocery Scenario 3 [6 apples and a bottle of milk, bought in 5 days time]")
    void scenario3() {
        ShoppingCart cart = new ShoppingCart(5);
        cart.add(StockItem.APPLE, 6);
        cart.add(StockItem.MILK);
        assertEquals(DecimalHelper.bigDecimalFromDoubleValue(1.84), cart.calculateTotalAfterDiscount());
    }

    @Test
    @DisplayName("Fun times Grocery Scenario 4 [3 apples, 2 tines of soup and a loaf of bread, bought in 5 days time]")
    void scenario4() {
        ShoppingCart cart = new ShoppingCart(5);
        cart.add(StockItem.APPLE, 3);
        cart.add(StockItem.SOUP, 2);
        cart.add(StockItem.BREAD);
        assertEquals(DecimalHelper.bigDecimalFromDoubleValue(1.97), cart.calculateTotalAfterDiscount());
    }

    @Test
    @DisplayName("Test getters")
    void testGetters() {
        ShoppingCart cart = new ShoppingCart(180);
        cart.add(StockItem.APPLE, 3);
        List<StockItem> items = cart.getItems();
        Set<StockItem> itemSet = new HashSet<>(items);
        LocalDateTime cartDate = cart.getCartDate();
        Map<StockItem, BigDecimal> discounts = cart.calculateDiscounts();
        assertAll(
                () -> assertEquals(3, items.size()),
                () -> assertEquals(1, itemSet.size()),
                () -> assertTrue(itemSet.contains(StockItem.APPLE)),
                () -> assertTrue(cartDate.isAfter(LocalDate.now().plusDays(180).atStartOfDay()), "cart date is after 180 days"),
                () -> assertTrue(cartDate.isBefore(LocalDate.now().plusDays(181).atStartOfDay()), "cart date is before 181 days"),
                () -> assertEquals(Collections.emptyMap(), discounts)
        );
    }

}