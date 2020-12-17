package com.ford.exercise.shoppingcart.cart;

import com.ford.exercise.shoppingcart.cart.exception.InvalidChartIteException;
import com.ford.exercise.shoppingcart.dicount.*;
import com.ford.exercise.shoppingcart.inventory.InventoryRepository;
import com.ford.exercise.shoppingcart.inventory.StockItem;
import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ShoppingCart {

    private List<ShoppingCartItem> items;

    private LocalDate orderDate = LocalDate.now();

    private CurrencyUnit currency = Monetary.getCurrency("GBP");

    private InventoryRepository inventoryRepository;

    private DiscountRepository discountRepository;

    public ShoppingCart() {
        this.items = new ArrayList<ShoppingCartItem>();
    }

    public List<ShoppingCartItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCartItem> items) {
        this.items = items;
    }

    public void add(ShoppingCartItem item) {
        this.items.add(item);
    }

    public void add(String product, Integer quantity) throws Exception {
        ShoppingCartItem item = get(product.trim());
        if(item != null) {
            item.setQuantity(item.getQuantity()+quantity);
        }
        StockItem stockItem = inventoryRepository.get(product.trim());
        if(stockItem == null) {
            throw new InvalidChartIteException("Invalid product");
        }

        item = new ShoppingCartItem();
        item.setQuantity(quantity);
        item.setCost(Money.of(stockItem.getCost(), currency));
        item.setStockItem(stockItem);
        this.items.add(item);
    }



    public InventoryRepository getInventoryRepository() {
        return inventoryRepository;
    }

    public void setInventoryRepository(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }





    public boolean isDiscountApplicable(Discount discount) {
        boolean isBetween = (orderDate.compareTo(discount.getValidFrom()) >= 0 && orderDate.compareTo(discount.getValidTo()) <= 0) ? true : false;
        return isBetween && isConditionApplicable(discount.getCondition()) && isRewardApplicable(discount.getReward()) ? true : false;
    }

    public ShoppingCartItem getConditionItem(DiscountCondition condition) {

        ShoppingCartItem result = items.stream()
                .filter(item -> condition.getProduct().equals(item.getStockItem().getProduct()) && item.getQuantity() >= condition.getQuantity())
                .findAny()
                .orElse(null);
        return result;
    }

    public boolean isConditionApplicable(DiscountCondition condition) {
        return getConditionItem(condition) != null ? true : false;
    }

    public ShoppingCartItem getRewardItem(DiscountReward reward) {
        ShoppingCartItem result = items.stream()
                .filter(item -> reward.getProduct().equals(item.getStockItem().getProduct()) && item.getQuantity() >= reward.getQuantity())
                .findAny()
                .orElse(null);

        return result;
    }

    public boolean isRewardApplicable(DiscountReward reward) {
        return getRewardItem(reward) != null ? true : false;
    }

    public ShoppingCartItem get(String key) {
        return items.stream()
                .filter(item -> key.equals(item.getStockItem().getProduct()))
                .findAny()
                .orElse(null);
    }

    public DiscountRepository getDiscountRepository() {
        return discountRepository;
    }

    public void setDiscountRepository(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public Money getNetTotal() {
        return getGrossTotal().subtract(getDiscountTotal());
    }

    public Money getGrossTotal() {
        return items.stream()
                .map(ShoppingCartItem::getTotalCost)
                .reduce((totalCostA, totalCostB) -> totalCostA.add(totalCostB))
                .orElse(Money.of(0, currency));
    }


    public Money getDiscountTotal() {
        Money total = Money.of(0.00, currency);
        for(ShoppingCartItem item: items) {
            List<Discount> itemDiscounts = discountRepository.find(item.getStockItem().getProduct());
            total = total.add(getDiscountTotal(itemDiscounts, item));
        }

        return total;
    }

    public Money getDiscountTotal(List<Discount> itemDiscounts, ShoppingCartItem item) {
        Money total = Money.of(0.00, currency);
        for(Discount discount: itemDiscounts) {
            Money result = getItemDiscount(discount, item);
            total = total.add(result);
        }
        return total;
    }


    public Money getItemDiscount(Discount discount, ShoppingCartItem item) {
        String rewardProduct = discount.getReward().getProduct();
        if(rewardProduct.equalsIgnoreCase(item.getStockItem().getProduct()) && isDiscountApplicable(discount)) {
            String conditionProduct = discount.getCondition().getProduct();

            ShoppingCartItem conditionItem = conditionProduct.equalsIgnoreCase(item.getStockItem().getProduct()) ? item : get(discount.getCondition().getProduct());
            Money result = discount.calculate(conditionItem, item);
            return result;
        }
        return Money.of(0, currency);
    }

    public CurrencyUnit getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyUnit currency) {
        this.currency = currency;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
}
