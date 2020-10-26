package funtimes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class ShoppingCart {

    private final List<StockItem> items;
    private final LocalDateTime cartDate;

    public ShoppingCart() {
        this(LocalDateTime.now());
    }

    public List<StockItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public LocalDateTime getCartDate() {
        return cartDate;
    }

    public ShoppingCart(Integer daysInFuture) {
        this(LocalDateTime.now().plusDays(daysInFuture));
    }

    private ShoppingCart(LocalDateTime cartDate) {
        items = new ArrayList<>();
        this.cartDate = cartDate;
    }

    public void add(StockItem item) {
        items.add(item);
    }

    public void add(StockItem item, Integer quantity) {
        for (int i = 0; i < quantity; i++) {
            items.add(item);
        }
    }

    protected BigDecimal calculateRunningTotalBeforeDiscount() {
        return DecimalHelper.bigDecimalFromDoubleValue(items
                .stream()
                .mapToDouble(item -> item.getCost().doubleValue())
                .sum());
    }

    public long unitsOf(StockItem item) {
        return items.stream()
                .filter(i -> i.getProduct().equals(item.getProduct()))
                .count();
    }

    public BigDecimal calculateTotalAfterDiscount() {

        BigDecimal runningTotal = calculateRunningTotalBeforeDiscount();
        Map<StockItem, BigDecimal> discounts = calculateDiscounts();
        double sumDiscounts = discounts
                .keySet()
                .stream()
                .mapToDouble(s -> discounts.get(s).doubleValue())
                .sum();
        return DecimalHelper.bigDecimalFromDoubleValue(runningTotal.doubleValue() - sumDiscounts);
    }

    protected Map<StockItem, BigDecimal> calculateDiscounts() {
        Map<StockItem, BigDecimal> discounts = new HashMap<>();
        for (Discount discount : Discount.OFFERS) {
            long nUnits = unitsOf(discount.getStockItem());
            if (isDiscountApplicable(nUnits, discount)) {
                if (discount.isContigentOn()) {
                    Discount.Contigent contigent = discount.getContigent();
                    StockItem contigentStockItem = contigent.getStockItem();
                    Integer contigentQuantity = discount.getContigent().getUnit();
                    long actualUnits = unitsOf(contigentStockItem);
                    long nApplicableDiscounts = actualUnits / contigentQuantity;
                    discounts.put(discount.getStockItem(), calculateDiscount(nApplicableDiscounts, discount.getStockItem().getCost(), discount.getDiscount()));
                } else {
                    discounts.put(discount.getStockItem(), calculateDiscount(nUnits, discount.getStockItem().getCost(), discount.getDiscount()));
                }
            }
        }
        return discounts;
    }

    private boolean isDiscountApplicable(long unitsInCart, Discount discount) {
        if (unitsInCart == 0) {
            return false;
        }

        if (cartDate.isBefore(discount.getValidFrom().atStartOfDay())) {
            return false;
        }

        return !cartDate.isAfter(discount.getValidTo().atStartOfDay());
    }

    private BigDecimal calculateDiscount(long nUnits, BigDecimal cost, BigDecimal offer) {
        return DecimalHelper.bigDecimalFromDoubleValue(nUnits * cost.doubleValue() * offer.doubleValue());
    }
}
